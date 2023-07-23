# Create the final Docker image with the built JAR file
FROM openjdk:19

# Set work directory
WORKDIR /app

# Copy jar file to the container
COPY build/libs/demo-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port on which your Spring Boot application runs
EXPOSE 8080

# Set the command to run the application, using wait-for-it to wait for MySQL
CMD java -jar app.jar
