FROM openjdk:8
VOLUME /temp
EXPOSE 8100
ADD ./target/Gateway-Service-0.0.1-SNAPSHOT.jar gateway-service.jar
ENTRYPOINT ["java","-jar","/gateway-service.jar"]