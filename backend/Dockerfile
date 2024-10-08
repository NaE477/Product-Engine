# Use the official Maven image to build the app
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app

# Copy the parent POM and backend source code
COPY backend/pom.xml backend/pom.xml
COPY backend/src backend/src

# Move to the backend directory and download the dependencies
WORKDIR /app/backend
RUN mvn dependency:go-offline -B

# Package the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# Use an official lightweight OpenJDK image to run the app
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the JAR file from the previous build stage
COPY --from=build /app/backend/target/*.jar app.jar

# Expose the application port (ensure the app is configured for this port)
EXPOSE 8086

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]