# Use a base image with the necessary dependencies (e.g., Java, Tomcat)
FROM openjdk:11-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the application JAR/WAR file to the container
COPY target/BookManagement-0.0.1-SNAPSHOT.jar .
COPY src/main/book.csv /app/book.csv

# Expose the port on which your application listens
EXPOSE 8080

# Specify the command to run your application
CMD ["java", "-jar", "BookManagement-0.0.1-SNAPSHOT.jar"]
