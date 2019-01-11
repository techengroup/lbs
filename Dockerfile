FROM ubuntu
MAINTAINER zy<zhangyue@techen.cn>

RUN mkdir /home/lbs
WORKDIR /opt
ADD jdk-8u191-linux-x64.tar.gz /opt/
ADD apache-karaf-4.1.6.tar.gz /opt/
COPY librxtxSerial.so /opt/jdk1.8.0_191/jre/lib/amd64/

ENV LBS_HOME /home/lbs
ENV JAVA_HOME /opt/jdk1.8.0_191
ENV KARAF_HOME /opt/apache-karaf-4.1.6
ENV PATH $PATH:$JAVA_HOME/bin:$KARAF_HOME/bin

EXPOSE 8101 5005
CMD karaf debug
#CMD ["/bin/bash" "-c" "rm -f /var/lock/LCK* && karaf debug"]
#CMD karaf debug
#ENTRYPOINT ["/bin/bash","-c"]
#CMD ["rm -f /var/lock/LCK*","karaf debug"]
