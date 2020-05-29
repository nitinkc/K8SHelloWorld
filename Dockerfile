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
FROM maven:3.6-jdk-8 as maven
WORKDIR /app
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline -B
COPY ./src ./src

#RUN mvn package && cp target/k8sHelloWorld-0.0.1-SNAPSHOT.jar app.jar
# Keeping Generic name to be used with other projects
RUN mvn package && cp target/*.jar app.jar

# Rely on Docker's multi-stage build to get a smaller image based on JRE
FROM openjdk:8-jdk-alpine
#LABEL maintainer="xxxxx@xxx.com"
WORKDIR /app
COPY --from=maven /app/app.jar ./app.jar

# optional
# VOLUME /tmp
EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]