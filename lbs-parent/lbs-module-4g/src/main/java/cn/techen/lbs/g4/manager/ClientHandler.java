package cn.techen.lbs.g4.manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.techen.lbs.db.common.GlobalUtil;
import cn.techen.lbs.g4.common.G4Context;
import cn.techen.lbs.g4.common.G4Util;
import cn.techen.lbs.g4.common.Local;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;

public class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	private static final Logger logger = LoggerFactory.getLogger(Local.PROJECT);
	
	private G4Context context;
	
	public ClientHandler(G4Context context) {
		this.context = context;
	}
	
	@Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
		logger.info("Channel connected {}.", ctx.channel().remoteAddress());
		context.fireLogin(ctx);
    }
	
	@Override
	protected void channelRead0(ChannelHandlerContext ctx, ByteBuf buf) throws Exception {		
		context.fireRead(G4Util.bufToByte(buf));
	}

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    	logger.info("Channel disconnected {}.", ctx.channel().remoteAddress());
    	context.fireLogout();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
    	logger.error("Channel caught exception.");
    	logger.error(GlobalUtil.getStackTrace(cause));
    }
	
	@Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = (IdleStateEvent) evt;
            switch (e.state()) {
                case ALL_IDLE:
                	context.fireHeartbeat();
                    break;
                default:
                    break;
            }
        }
    }

}
