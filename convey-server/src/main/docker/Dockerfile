FROM java:8
VOLUME /tmp
ADD convey-server-0.0.1-SNAPSHOT.jar /convey-server.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/convey-server.jar"]

#设置时区
ENV TZ=Asia/Shanghai
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone