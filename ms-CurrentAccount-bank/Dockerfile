FROM openjdk:8
VOLUME /temp
EXPOSE 8097
ADD ./target/ms-CurrentAccount-bank-0.0.1-SNAPSHOT.jar currentaccount-service.jar
ENTRYPOINT ["java","-jar","/currentaccount-service.jar"]