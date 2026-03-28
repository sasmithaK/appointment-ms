# Cloud Deployment Guide - Updated Microservices Architecture

This guide details exactly how to host the newly refactored Appointment Management System to fulfill the "Cloud Deployment" execution requirement, reflecting the strict logically-separated 4-service architecture.

## Decentralized Hosting Strategy
Hosting microservices separately is the most realistic cloud architecture. Platforms like **Render**, **Railway**, or **Heroku** are perfect for this as they natively build Docker pipelines from GitHub repositories.

### Prerequisite 1: Hosted Databases and Brokers
Because the services are physically separated, you must use managed cloud instances for your data layers:
1. **MongoDB**: Create a free cluster on **[MongoDB Atlas](https://www.mongodb.com/atlas/database)**. Get your connection string (e.g., `mongodb+srv://user:pass@cluster.mongodb.net/userdb`).
2. **RabbitMQ**: Create a free instance on **[CloudAMQP](https://www.cloudamqp.com/)**. Get your AMQP URL.

### Prerequisite 2: Deploy Infrastructure Services First
You must deploy the discovery services before the core microservices so they have an address book to register with.
1. **Eureka Server**:
   * Create a new "Web Service" on your cloud provider.
   * Connect your GitHub repository.
   * **Root Directory**: `eureka-server`
   * **Build Command**: `docker build -t eureka-server .` (Or select Docker as the runtime).
   * Once deployed, copy its public URL (e.g., `https://appointment-eureka.onrender.com`).

### Prerequisite 3: Deploy Core Microservices
Repeat the core deployment steps for the **4 Main Microservices**:
* `user-service`
* `doctor-service`
* `booking-service`
* `notification-service`

1. Create a "Web Service" for each.
2. Set the **Root Directory** to the respective service folder.
3. For each service, you **must set the following Environment Variables** in your hosting dashboard:
   * `SPRING_PROFILES_ACTIVE=docker`
   * `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE=https://appointment-eureka.onrender.com/eureka/` *(Replace with your deployed Eureka URL!)*
   * `SPRING_MONGODB_URI` = Your MongoDB Atlas string.
   * `SPRING_RABBITMQ_HOST` = Your CloudAMQP URL.

### Prerequisite 4: Deploy the API Gateway
1. Create the final "Web Service" setting the root directory to `api-gateway`.
2. Provide it the same `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE` variable.
3. The API Gateway will dynamically route requests to all your separately hosted microservices via Eureka. This will serve as your **single public endpoint** for the frontend application.

## Alternative: Single Instance Deployment (AWS EC2 Free Tier)
If you prefer to host everything on a single server to save time:
1. Launch an Ubuntu `t2.micro` or `t3.micro` EC2 instance.
2. SSH in and install Docker and Git.
3. Clone your repository: `git clone https://github.com/your-username/appointment-ms.git`
4. Run `docker compose up --build -d`
5. The API Gateway will be natively available at `http://<EC2-PUBLIC-IP>:8080/`.
