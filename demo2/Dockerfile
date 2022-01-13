FROM jenkins/jenkins:2.150.3
EXPOSE 8081
RUN ./gradlew bootrun && \ ./gradlew test
ADD /Users/macbookair/Desktop/Backend-Ev2/demo2/build/libs/demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]
