FROM openjdk:8
VOLUME /temp
EXPOSE 8095
ADD ./target/ms.transaction-bank-0.0.1-SNAPSHOT.jar transaction-service.jar
ENTRYPOINT ["java","-jar","/transaction-service.jar"]