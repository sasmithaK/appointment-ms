FROM eclipse-temurin:17-jdk-alpine

# Create a non-root user
RUN addgroup -S spring && adduser -S spring -G spring

VOLUME /tmp

# Copy jar
COPY target/*.jar app.jar

# Change ownership
RUN chown spring:spring app.jar

# Switch to non-root user
USER spring

ENTRYPOINT ["java","-jar","/app.jar"]
