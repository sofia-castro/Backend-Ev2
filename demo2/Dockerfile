FROM openjdk:11-oracle
ENV ARTIFACT_NAME=demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
COPY demo-0.0.1-SNAPSHOT.jar /demo-0.0.1-SNAPSHOT.jar
EXPOSE $PORT

ENV DOCKERVERSION=18.03.1-ce
RUN curl -fsSLO https://download.docker.com/mac/static/stable/x86_64/docker-${DOCKERVERSION}.tgz \
  && tar xzvf docker-${DOCKERVERSION}.tgz --strip 1 \
                 -C /usr/local/bin docker/docker \
  && rm docker-${DOCKERVERSION}.tgz