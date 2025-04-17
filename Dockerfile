#########################################################################
######################## BUILD FROM DOCKER HUB ##########################
#########################################################################

# Use Gradle image with JDK 21 for building and running the application

# Use the official Gradle image to build the project

# Use the official Gradle image to build the project
FROM gradle:8.13.0-jdk21 AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Gradle wrapper and build files
COPY gradle /app/gradle
COPY gradlew /app/gradlew
COPY build.gradle /app/build.gradle
COPY settings.gradle /app/settings.gradle

# Copy the source code
COPY src /app/src

# Build the project
RUN ./gradlew build

# Use the official OpenJDK image to run the application
FROM openjdk:21-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","app.jar"]
