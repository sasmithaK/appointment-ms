# Appointment Management System - API & Functionality Documentation

This document outlines the core responsibilities, functionalities, and API endpoints for every microservice within the Appointment Management System architecture.

---

## 1. API Gateway (`api-gateway`)
**Port:** `8080` (Default entry point for frontends/clients)
**Functionality:** 
Acts as the single entry point to the system. It intercepts all incoming client requests, resolves the correct backend microservice using the Eureka Server registry, and routes the traffic dynamically.
* **Routing Strategy**: Requests matching `/<service-name>/**` are forwarded to the respective microservice.

---

## 2. User Service (`user-service`)
**Port:** `8081` (Internal)
**Functionality:** 
Responsible for identity and access management. Handles patient and admin registrations, secure login, password hashing, and JWT token issuing. It stores user profiles securely in MongoDB.

### API Endpoints
| HTTP Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/users/register` | Registers a new user (Patient/Doctor/Admin) | No |
| `POST` | `/api/users/login` | Authenticates user and returns JWT Token | No |
| `GET` | `/api/users/{id}` | Fetches a user's complete profile | Yes |

---

## 3. Booking Service (`booking-service`)
**Port:** `8082` (Internal)
**Functionality:** 
The core engine of the system. It manages the lifecycle of appointments and available time slots. When an appointment is booked, it communicates synchronously with the User/Doctor services (via OpenFeign) to validate entities, and publishes asynchronous events to RabbitMQ for notification processing.

### API Endpoints
**Appointments:**
| HTTP Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/appointments` | Books a new appointment for a user with a doctor | Yes |
| `GET` | `/appointments/{id}` | Retrieves details of a specific appointment | Yes |
| `GET` | `/appointments/user/{userId}` | Retrieves history of a patient's appointments | Yes |
| `PUT` | `/appointments/{id}/status` | Updates status (e.g., Cancel, Complete) | Yes |

**Slots (Available Time Management):**
| HTTP Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/slots` | Creates a new appointment slot | Yes (Admin) |
| `GET` | `/slots/available` | Lists all open slots available for booking | Yes |
| `PUT` | `/slots/{slotId}` | Modifies a slot's time or assigned doctor | Yes (Admin) |
| `DELETE` | `/slots/{slotId}` | Removes a slot entirely | Yes (Admin) |

---

## 4. Doctor Service (`doctor-service`)
**Port:** `8085` (Internal)
**Functionality:** 
Manages the medical staff records and doctor-specific operational schedules. Stores doctor specializations, bios, and specific daily shifts.

### API Endpoints
**Doctors:**
| HTTP Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/doctors` | Registers a new doctor into the system | Yes (Admin) |
| `GET` | `/doctors` | Retrieves the public directory of all doctors | Yes |
| `GET` | `/doctors/{doctorId}` | Retrieves a specific doctor's profile | Yes |

**Schedules:**
| HTTP Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/schedules` | Creates a working schedule for a doctor | Yes (Admin) |
| `GET` | `/schedules` | Lists all master schedules | Yes |
| `GET` | `/schedules/available` | Lists all unbooked schedule chunks | Yes |

---

## 5. Notification Service (`notification-service`)
**Port:** `8083` (Internal)
**Functionality:** 
A strictly event-driven microservice. It does not accept direct creation requests via HTTP. Instead, it continuously listens to the `appointment-notification-queue` on RabbitMQ. When a slot is booked or canceled, this service catches the event and writes the notification to its independent MongoDB instance for user retrieval.

### API Endpoints
| HTTP Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/notifications/user/{recipientId}` | Retrieves a chronological list of all notifications (alerts/confirmations) for a specific user | Yes |

---

## Infrastructure Services

### Service Registry (`eureka-server`)
**Port:** `8761`
Acts as the dynamic DNS for the microservices. Every service registers its IP and Port here on startup, allowing them to find each other without hardcoded IPs.

### Distributed Configuration (`config-server`)
**Port:** `8888`
Centralizes environment variables and application properties. It securely feeds database credentials, RabbitMQ URIs, and JWT keys to all microservices on launch.
