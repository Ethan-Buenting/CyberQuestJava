FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy all project files
COPY . .

# Build the app using Maven Wrapper
RUN ./mvnw clean install

# Run the Spring Boot app
CMD ["java", "-jar", "target/*.jar"]