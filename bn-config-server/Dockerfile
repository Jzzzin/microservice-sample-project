FROM openjdk:8-jdk-alpine
ENV ENCRYPT_KEY blOkn0m@EncKey
RUN apk --no-cache add curl unzip
RUN cd /tmp/ && \
    wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip" && \
    unzip -jo -d ${JAVA_HOME}/lib/security jce_policy-8.zip && \
    rm -rf /tmp/*

COPY .ssh/bitbucket_rsa /root/.ssh/bitbucket_rsa
COPY .ssh/config /root/.ssh/config
COPY .ssh/known_hosts /root/.ssh/known_hosts
RUN chmod 400 /root/.ssh/bitbucket_rsa

CMD java ${JAVA_OPTS} -jar bn-config-server.jar
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8080/actuator/health || exit 1
COPY build/libs/bn-config-server.jar .