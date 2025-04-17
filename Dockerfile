# BUILD FROM LOCAL SETUP
#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#EXPOSE 8080
#ADD target/*.jar app.jar
#ENV JAVA_OPTS=""
#ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]


#########################################################################
######################## BUILD FROM DOCKER HUB ##########################
#########################################################################
FROM gradle:7.6-jdk21 as gradle
WORKDIR /app
COPY ./build.gradle ./settings.gradle ./
RUN gradle build --no-daemon
COPY ./src ./src

# Keeping Generic name to be used with other projects
RUN gradle build && cp build/libs/*.jar app.jar

# Rely on Docker's multi-stage build to get a smaller image based on JRE
FROM openjdk:21-jdk-alpine
WORKDIR /app
COPY --from=gradle /app/app.jar ./app.jar

# optional
# VOLUME /tmp
EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
