FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy all project files
COPY . .

# Build the app using Maven Wrapper
RUN ./mvnw clean package

# Run the Spring Boot app
CMD ["java", "-jar", "target/CPRE530-0.0.1-SNAPSHOT.jar"]