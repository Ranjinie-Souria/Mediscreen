FROM openjdk:19-alpine

COPY target/Patients-0.0.1-SNAPSHOT.jar patient-api-0.0.1.jar

EXPOSE 8084
ENTRYPOINT ["java","-jar","/patient-api-0.0.1.jar"]