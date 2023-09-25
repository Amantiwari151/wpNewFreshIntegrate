# Use a base image containing Java 11 (HotSpot variant)
FROM adoptopenjdk:11-jre-hotspot

# Expose port 9095 for the application to the outside world
EXPOSE 9095

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file (your Helidon application) from your local machine into the container at /app
COPY target/wpintegration.jar wpintegration.jar

# Define the command to run when the container starts
CMD ["java", "-jar", "wpintegration.jar"]
