# Appointment Management System (Microservices Architecture)

A robust, enterprise-grade Appointment Management System built with Spring Boot, fully embracing a decentralized Microservices Architecture. 

## 🚀 Key Features and Architecture

The system is strictly divided into 4 core domain services and 2 infrastructure services, seamlessly communicating via REST and Event-Driven patterns.

- **Microservices Separation**: Logically decoupled into `user-service`, `doctor-service`, `booking-service`, and `notification-service`.
- **API Gateway Edge Server**: Centralized public entry point (`8080`) with dynamic routing to keep backend service ports hidden and secure.
- **Service Discovery (Netflix Eureka)**: Dynamic container registration and auto-discovery, eliminating hardcoded IPs (`8761`).
- **Distributed Configuration (Spring Cloud Config)**: Centralized management of database URIs and environment properties (`8888`).
- **Synchronous Communication**: Direct interservice REST calls using **Spring Cloud OpenFeign** for immediate validation (e.g., Booking -> User).
- **Asynchronous Event-Driven Messaging**: **RabbitMQ** integration for non-blocking workflows (e.g., publishing `appointment.booked` events to trigger notifications independently).
- **Persistent Storage**: Independent MongoDB clusters for services ensuring absolute data encapsulation.
- **API Documentation & Health Monitoring**: Fully instrumented with **Swagger OpenAPI** (`/swagger-ui.html`) and **Spring Boot Actuator** (`/actuator/health`) across all microservices.

## 🛠 Project Structure

```text
appointment-ms/
├── api-gateway/          # Spring Cloud Gateway (Port 8080)
├── config-server/        # Centralized Properties (Port 8888)
├── eureka-server/        # Service Registry (Port 8761)
├── user-service/         # User Management & Auth (Port 8081)
├── booking-service/      # Appointment Booking & OpenFeign (Port 8082)
├── doctor-service/       # Doctor Profiles & Schedules (Port 8085)
├── notification-service/ # RabbitMQ Event Subscriber (Port 8083)
├── docker-compose.yml    # Offline Full-System Orchestration
└── pom.xml               # Multi-Module Maven Aggregator
```

## 🏗 Setup & Running Locally

### Option 1: Full System (Docker Compose)
This is the easiest way to launch the entire ecosystem (6 Java Microservices + MongoDB + RabbitMQ) offline.

```bash
docker compose up --build -d
```
- **API Gateway Homepage**: `http://localhost:8080/`
- **Eureka Dashboard**: `http://localhost:8761/`
- **RabbitMQ Dashboard**: `http://localhost:15672/` (guest/guest)

### Option 2: Cloud Deployment (Render/Railway/AWS)
Because this is a true Microservices architecture, you can host every service separately on the cloud. 
1. Provision **MongoDB Atlas** and **CloudAMQP** (RabbitMQ).
2. Deploy **Eureka Server** first natively via Docker.
3. Deploy the **Core Services** and supply `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`, `SPRING_MONGODB_URI`, and `SPRING_RABBITMQ_HOST` explicitly in the container environment variables.
4. Deploy the **API Gateway** last to act as the public cloud entry point.

*(Refer to `docs/API_DOCUMENTATION.md` for a complete list of endpoints and `docs/final_deployment_guide.md` for detailed hosting instructions).*

## 🧪 Testing & Interactive Documentation

All underlying REST endpoints are natively documented. You can execute requests directly from your browser by navigating to the Swagger UI for any running service:

- User Service API: `http://localhost:8081/swagger-ui.html`
- Doctor Service API: `http://localhost:8085/swagger-ui.html`
- Booking Service API: `http://localhost:8082/swagger-ui.html`
- Gateway API: `http://localhost:8080/swagger-ui.html`
