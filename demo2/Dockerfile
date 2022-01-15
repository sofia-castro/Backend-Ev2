FROM openjdk:11-oracle
ENV ARTIFACT_NAME=demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
COPY demo-0.0.1-SNAPSHOT.jar /demo-0.0.1-SNAPSHOT.jar
RUN curl -fsSLO https://get.docker.com/builds/Linux/x86_64/docker-17.04.0-ce.tgz \
  && tar xzvf docker-17.04.0-ce.tgz \
  && mv docker/docker /usr/local/bin \
  && rm -r docker docker-17.04.0-ce.tgz
EXPOSE $PORT