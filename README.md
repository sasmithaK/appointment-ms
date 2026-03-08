# Appointment Management System

A modern, robust microservices-based Appointment Management System built with Spring Boot, MongoDB, and Docker.

## 🚀 Key Features

- **Microservices Architecture**: Decoupled services for User, Booking, Notification, and Appointment management.
- **API Gateway**: Centralized entry point with dynamic routing and health monitoring.
- **Spring Profiles**: Seamless transition between `local` and `docker` environments.
- **Observability**: Spring Boot Actuator integrated into all services for health checks.
- **Persistent Storage**: MongoDB with Docker volumes to ensure data persists across restarts.
- **Developer Experience**: 
  - Multi-stage Docker builds for guaranteed fresh artifacts.
  - Mongo Express UI for easy database inspection.
  - Standardized configuration across all services.

## 🛠 Project Structure

```text
appointment-ms/
├── api-gateway/          # Spring Cloud Gateway (Port 8080)
├── user-service/         # User Management & Auth (Port 8081)
├── booking-service/      # Booking Management (Port 8082)
├── notification-service/ # Notification Handling (Port 8083)
├── appointment-service/  # Appointment Scheduling (Port 8084)
├── docker-compose.yml    # Full system orchestration
└── pom.xml               # Parent Maven aggregator
```

## 🏗 Setup & Running

### Prerequisites
- Java 17+
- Maven
- Docker & Docker Compose

### Option 1: Full System (Docker)
This is the easiest way to run the entire system with one command.

```bash
docker-compose up --build
```
- **API Gateway**: `http://localhost:8080`
- **Mongo Express UI**: `http://localhost:8085`
- **MongoDB**: `localhost:27017`

### Option 2: Individual Services (Local)
For development, you can run services individually against a local MongoDB.

1. **Start MongoDB**: `docker run -p 27017:27017 mongo`
2. **Run a Service**:
   ```bash
   cd user-service
   mvn spring-boot:run
   ```
   *The service will automatically use the `default` profile and connect to `localhost:27017`.*

## 📈 Upcoming Features
- [ ] **Service Discovery**: Integrate Netflix Eureka for dynamic service registration.
- [ ] **Distributed Tracing**: Implement Zipkin/Sleuth for request tracking.
- [ ] **Resilience**: Add Resilience4j for circuit breakers and retries.
- [ ] **Security**: Implement Spring Security with OAuth2/OIDC.
- [ ] **Messaging**: Integrate RabbitMQ or Kafka for asynchronous notifications.

## 🧪 Testing Endpoints
All endpoints are accessible via the Gateway (8080) or directly:
- **Health**: `GET http://localhost:8080/users/actuator/health`
- **Register**: `POST http://localhost:8080/users/auth/register`
- **Login**: `POST http://localhost:8080/users/auth/login`
- **Bookings**: `GET http://localhost:8080/bookings/user/{userId}`
