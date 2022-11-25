FROM openjdk:19-alpine

COPY target/Notes-0.0.1-SNAPSHOT.jar notes-api-0.0.1.jar
EXPOSE 8082
EXPOSE 27017
ENTRYPOINT ["java","-jar","/notes-api-0.0.1.jar"]