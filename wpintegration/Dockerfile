# Use a base image containing Java 11 (HotSpot variant)
FROM adoptopenjdk:11-jre-hotspot

# Expose port 9095 for the application to the outside world
EXPOSE 9095

# Copy the JAR file from your local machine into the container
# The assumption here is that you have the "wpintegration.jar" file in the "target" directory of your project.
ADD target/wpintegration.jar wpintegration.jar

# Define the command to run when the container starts
ENTRYPOINT ["java", "-jar", "wpintegration.jar"]
