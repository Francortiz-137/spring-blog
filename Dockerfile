# Use OpenJDK 21 as base image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Specify the location of the JAR file
ARG JAR_FILE=target/blogSpring-0.0.1-SNAPSHOT.jar

# Copy the JAR file into the container
COPY ${JAR_FILE} app_blog.jar

# Expose the app port
EXPOSE 9000

# Command to run the JAR
ENTRYPOINT ["java", "-jar", "app_blog.jar"]
