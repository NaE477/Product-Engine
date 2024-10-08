# Use the official Maven image to build the app
FROM maven:3.9.4-eclipse-temurin-21 AS build

# Set the working directory
WORKDIR /app/backend

# Copy the backend POM and source code
COPY pom.xml .
COPY src ./src

# Move to the backend directory and download the dependencies
RUN mvn dependency:go-offline -B

# Package the application (skip tests for faster build)
RUN mvn clean package -DskipTests

# Use an official lightweight OpenJDK image to run the app
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app

# Copy the JAR file from the previous build stage
COPY --from=build /app/backend/target/naeim-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port (ensure the app is configured for this port)
EXPOSE 8086

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]