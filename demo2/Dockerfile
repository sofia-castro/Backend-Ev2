FROM adoptopenjdk/openjdk11:ubi
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY build/libs/*.jar
EXPOSE 8080
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]