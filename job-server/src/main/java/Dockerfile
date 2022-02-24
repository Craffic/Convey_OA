FROM java:8
VOLUME /tmp
ADD vue-test-0.0.1-SNAPSHOT.jar /vue-test.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/vue-test.jar"]