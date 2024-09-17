# Use the official Maven image to build the app
FROM maven:latest AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file and download the project dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the source code to the container
COPY backend .

# Package the application
RUN mvn clean package -DskipTests

# Use an official OpenJDK image as a base
FROM openjdk:22

# Set the working directory
WORKDIR /app

# Copy the jar file from the build image
COPY --from=build app/target/naeim-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8086

# Run the application
CMD ["java", "-jar", "app.jar"]