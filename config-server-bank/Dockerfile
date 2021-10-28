FROM openjdk:8
VOLUME /temp
EXPOSE 8762
ADD ./target/config-server-bank-0.0.1-SNAPSHOT.jar config-server.jar
ENTRYPOINT ["java","-jar","/config-server.jar"]