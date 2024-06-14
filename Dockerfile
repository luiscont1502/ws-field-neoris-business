FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/ws-field-neoris-business-0.0.1-SNAPSHOT.war business-service.war
ENTRYPOINT ["java","-jar","/business-service.war"]

