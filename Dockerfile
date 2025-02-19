#stage 1: build
# Start jdk maven 17

FROM maven:3.9.8-amazoncorretto-17 AS build

# Copy the source code and pom.xml to folder /app
WORKDIR /app
COPY pom.xml .
COPY src ./src

# Build the application
RUN mvn clean package -DskipTests

#stage 2: create the final image
FROM amazoncorretto:17.0.14

# Copy the jar file from the build stage to the new image
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
