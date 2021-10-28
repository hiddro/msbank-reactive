FROM openjdk:8
VOLUME /temp
EXPOSE 8099
ADD ./target/ms-Transfer-bank-0.0.1-SNAPSHOT.jar transfer-service.jar
ENTRYPOINT ["java","-jar","/transfer-service.jar"]