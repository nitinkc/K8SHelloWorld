#########################################################################
######################## BUILD FROM DOCKER HUB ##########################
#########################################################################

# Use Gradle image with JDK 21 for building and running the application
FROM gradle:7.6-jdk21
WORKDIR /app

# Copy Gradle build files and source code
COPY ./build.gradle ./settings.gradle ./
COPY ./src ./src

# Build the application
RUN gradle build --no-daemon

# Copy the built JAR file to the current directory
RUN cp build/libs/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/app.jar"]
