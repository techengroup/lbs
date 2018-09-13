package cn.techen.lbs.g4.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.g4.common.G4Context;
import cn.techen.lbs.g4.common.Local;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.FixedRecvByteBufAllocator;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class Client {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private G4Context context;
	public String host;
	public int port;
	
	public Client(G4Context context) {
		this.context = context;
	}
	
	public void connect(String host, int port) {
		setRemoteAddress(host, port);
		EventLoopGroup group = new NioEventLoopGroup();
		
		try {
            Bootstrap b = new Bootstrap();
            b.group(group);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.TCP_NODELAY, true);
//            b.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 3000); new AdaptiveRecvByteBufAllocator(512, 1024, 4096)
            b.option(ChannelOption.RCVBUF_ALLOCATOR, new FixedRecvByteBufAllocator(1024));
            b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				protected void initChannel(SocketChannel socketChannel) throws Exception {
					ChannelPipeline p = socketChannel.pipeline();
					p.addLast("logging", new LoggingHandler(LogLevel.INFO));
                    p.addLast(new IdleStateHandler(0, 0, 60));
                    p.addLast(new ClientHandler(context));
				}               
            });
  
            logger.info("LBS is creating a connection about server/{}:{}.", host, port);
            ChannelFuture f = b.connect(host, port).sync();
            
            f.channel().closeFuture().sync();
        } catch (Exception e) {
        	logger.error("LBS occur exception.", host, port);
        	logger.error(GlobalUtil.getStackTrace(e));
        } finally {
        	logger.info("LBS shutdown gracefully.", host, port);        	
            group.shutdownGracefully();
        }
	}
	
	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}
	
	private void setRemoteAddress(String host, int port) {
		this.host = host;
		this.port = port;
	}

}
