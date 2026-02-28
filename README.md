# Appointment Management System

Microservices example composed of multiple Spring Boot services, an API gateway, and Docker compose orchestration.

## Repository layout

- [pom.xml](pom.xml) — Root Maven aggregator
- [docker-compose.yml](docker-compose.yml) — Orchestration for local development
- [api-gateway](api-gateway/) — API Gateway service
- [appointment-service](appointment-service/) — Appointment service
- [booking-service](booking-service/) — Booking service
- [notification-service](notification-service/) — Notification service
- [user-service](user-service/) — User service
- [.github/workflows](.github/workflows/) — CI workflows

## Requirements

- Java 17+
- Maven (or use provided wrapper `./mvnw`)
- Docker & Docker Compose (for containerized run)

## Build

From repository root:

```sh
./mvnw -v
./mvnw clean package
```

You can also build an individual service:

```sh
cd appointment-service
./mvnw clean package
```

## Run (local, JVM)

Run a single service for development:

```sh
cd appointment-service
./mvnw spring-boot:run
```
