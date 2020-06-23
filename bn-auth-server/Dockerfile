FROM openjdk:8-jdk-alpine
ENV ENCRYPT_KEY blOkn0m@EncKey
ENV CLIENT_SECRET blOkn0m@CliScr
RUN apk --no-cache add curl
CMD java ${JAVA_OPTS} -jar bn-auth-server.jar
HEALTHCHECK --start-period=30s --interval=5s CMD curl -f http://localhost:8080/actuator/health || exit 1
COPY build/libs/bn-auth-server.jar .