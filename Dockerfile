#########################################################################
######################## BUILD FROM DOCKER HUB ##########################
#########################################################################
FROM gradle:7.6-jdk21 as gradle
WORKDIR /app
COPY ./build.gradle ./settings.gradle ./
COPY ./src ./src

# Keeping Generic name to be used with other projects
RUN gradle build && cp build/libs/*.jar app.jar

# Rely on Docker's multi-stage build to get a smaller image based on JRE
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=gradle /app/app.jar ./app.jar

# optional
# VOLUME /tmp
EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
