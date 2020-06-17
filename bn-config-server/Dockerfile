FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl unzip
RUN cd /tmp/ && \
    wget --no-check-certificate --header "Cookie: oraclelicense=accept-securebackup-cookie" "http://download.oracle.com/otn-pub/java/jce/8/jce_policy-8.zip" && \
    unzip -jo -d ${JAVA_HOME}/lib/security jce_policy-8.zip && \
    rm -rf /tmp/*

CMD java ${JAVA_OPTS} -jar bn-config-server.jar
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8080/actuator/health || exit 1
COPY build/libs/bn-config-server.jar .