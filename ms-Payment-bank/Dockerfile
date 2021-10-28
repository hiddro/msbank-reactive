FROM openjdk:8
VOLUME /temp
EXPOSE 8092
ADD ./target/ms-Payment-bank-0.0.1-SNAPSHOT.jar payment-service.jar
ENTRYPOINT ["java","-jar","/payment-service.jar"]