FROM openjdk:11-oracle
ENV ARTIFACT_NAME=demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT exec java -jar ${ARTIFACT_NAME}
COPY demo-0.0.1-SNAPSHOT.jar /demo-0.0.1-SNAPSHOT.jar
COPY plugins.sh /usr/local/bin/plugins.sh
EXPOSE $PORT
EXPOSE ${http_port}

# will be used by attached slave agents:
EXPOSE ${agent_port}

ENV COPY_REFERENCE_FILE_LOG $JENKINS_HOME/copy_reference_file.log

USER ${user}

COPY jenkins-support /usr/local/bin/jenkins-support
COPY jenkins.sh /usr/local/bin/jenkins.sh
ENTRYPOINT ["/bin/tini", "--", "/usr/local/bin/jenkins.sh"]
COPY install-plugins.sh /usr/local/bin/install-plugins.sh