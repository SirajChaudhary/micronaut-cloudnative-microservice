# Healthcare Appointment Service

A cloud-native Healthcare Appointment Management System built using **Micronaut**, **Micronaut Data Hibernate JPA**, and **PostgreSQL**.

The application demonstrates how to build a modern RESTful microservice using Micronaut with compile-time dependency injection, compile-time repository generation, fast startup, low memory usage, and cloud-native capabilities.

It provides REST APIs to manage hospitals, doctors, patients, and appointments, along with an advanced doctor search supporting partial search, filtering, pagination, and sorting.

The project intentionally focuses on showcasing Micronaut features and best practices while keeping the implementation clean, simple, and production-ready.

---

# Project Overview

The application demonstrates several backend development concepts commonly used in enterprise applications, including RESTful API design, Micronaut Data JPA, JPQL, OpenAPI, Docker, GraalVM Native Image, pagination, sorting, filtering, and Package by Feature architecture.

### What can this application do?

**Hospital Management**

- Create hospitals
- View hospital details
- Update hospital information
- Delete hospitals
- List all hospitals

**Doctor Management**

- Create doctors
- View doctor details
- Update doctor information
- Delete doctors
- List all doctors
- Search doctors using multiple search criteria

**Patient Management**

- Register patients
- View patient details
- Update patient information
- Delete patients
- List all patients

**Appointment Management**

- Create appointments
- View appointment details
- Update appointments
- Cancel appointments
- List all appointments

### Highlights

- RESTful CRUD APIs
- Micronaut Framework
- Micronaut Data Hibernate JPA
- PostgreSQL
- JPQL-based Advanced Search
- Case-insensitive Partial Search
- Multiple Filters
- Pagination
- Multi-column Sorting
- OpenAPI Documentation
- Swagger UI
- Docker Ready
- GraalVM Native Image Ready
- Package by Feature Architecture
- Compile-time Dependency Injection
- Compile-time Repository Generation

### Architecture

```text
                    +----------------------+
                    |     REST Client      |
                    | Browser / Postman    |
                    +----------+-----------+
                               |
                               |
                     HTTP REST APIs
                               |
                               ▼
                    +----------------------+
                    | Micronaut Controller |
                    +----------+-----------+
                               |
                               ▼
                    +----------------------+
                    |       Service        |
                    |    Business Logic    |
                    +----------+-----------+
                               |
                               ▼
                    +----------------------+
                    | Micronaut Data JPA   |
                    |     Repository       |
                    +----------+-----------+
                               |
                               ▼
                    +----------------------+
                    |     PostgreSQL       |
                    +----------------------+
```

### Technology Stack

| Technology | Purpose |
|------------|---------|
| Java 21 | Programming Language |
| Micronaut 4.10.17 | Cloud-Native Microservice Framework |
| Micronaut Data Hibernate JPA | Compile-time JPA Repository Implementation |
| Hibernate | JPA Provider |
| PostgreSQL | Relational Database |
| Maven | Build Tool |
| OpenAPI | API Documentation |
| Swagger UI | Interactive API Testing |
| Docker | Containerization |
| GraalVM | Native Executable Generation |
| Netty | Default Embedded HTTP Server |

### Project Structure

The project follows the **Package by Feature** approach instead of the traditional **Package by Layer** approach.

Instead of separating controllers, services, and repositories into different packages, all classes related to a business feature are grouped together in a single package.

This approach makes the project easier to understand, maintain, test, and extend as the application grows.

Benefits

- Better organization
- Easier navigation
- Better feature isolation
- Higher maintainability
- Easier onboarding for new developers
- Scales well for medium and large applications
- Encourages modular development

```text
src/main/java
└── com.sirajchaudhary.healthcare
    ├── appointment
    ├── common
    ├── config
    ├── doctor
    ├── hospital
    └── patient
```

### Features Demonstrated

| Feature | Description |
|----------|-------------|
| CRUD APIs | Create, Read, Update and Delete operations for hospitals, doctors, patients and appointments. |
| Micronaut Data Hibernate JPA | Demonstrates compile-time generated JPA repositories using `micronaut-data-hibernate-jpa`. |
| Compile-time Dependency Injection | Dependencies are resolved during compilation instead of runtime. |
| Compile-time Repository Generation | Repository implementations are generated during compilation, reducing runtime overhead. |
| JPQL | Custom JPQL queries for advanced data retrieval and filtering. |
| Advanced Search | Search doctors using keyword search, multiple filters, pagination and sorting. |
| Partial Search | Case-insensitive substring search across doctor first name, last name, specialization and email. |
| Exact Filters | Filter doctors by hospital, specialization, minimum experience, minimum rating and active status. |
| Pagination | Efficiently retrieve large datasets page by page using `Pageable`. |
| Sorting | Supports single-column and multi-column sorting in ascending or descending order. |
| Entity Relationships | Demonstrates relationships between Hospital, Doctor, Patient and Appointment entities using JPA. |
| OpenAPI | Automatically generates OpenAPI documentation from the source code. |
| Swagger UI | Interactive web interface for exploring and testing REST APIs. |
| Docker | Supports containerized deployment using Docker. |
| GraalVM Native Image | Ready for native executable generation with faster startup and lower memory usage. |
| Package by Feature | Organizes code by business capability for better maintainability and scalability. |

---

# What is Micronaut

Micronaut is a modern JVM-based framework for building cloud-native applications, microservices, serverless applications, REST APIs, and event-driven systems.

Unlike many traditional Java frameworks, Micronaut performs most dependency injection, bean creation, and annotation processing at **compile time** instead of runtime. This significantly reduces application startup time, memory consumption, and reflection usage.

Micronaut is an excellent choice for modern cloud environments where applications need to start quickly, consume fewer resources, and scale efficiently.

### History

- Developed by **Object Computing, Inc. (OCI)**.
- First released in **2018**.
- Created by the founders of the **Grails Framework**.
- Designed specifically for cloud-native and serverless workloads.
- Supports Java, Kotlin and Groovy.

### Key Features

- Lightweight framework
- Compile-time Dependency Injection (DI)
- Compile-time Aspect-Oriented Programming (AOP)
- Reflection-free design wherever possible
- Fast application startup
- Low memory usage
- Cloud-native architecture
- Serverless support
- GraalVM Native Image support
- Micronaut Data
- Reactive programming support
- Event-driven application support
- OpenAPI integration
- Embedded Netty HTTP Server
- Production-ready monitoring and management features

### Advantages

- Very fast application startup
- Lower memory usage than traditional frameworks
- Excellent GraalVM support
- Compile-time dependency injection
- Less runtime reflection
- Smaller Docker images
- Faster cold starts for serverless applications
- Well suited for microservices
- Excellent documentation and official guides
- Supports Java, Kotlin and Groovy

### Limitations

- Smaller ecosystem than Spring Boot
- Smaller community
- Fewer third-party integrations
- Some advanced features are still evolving
- Learning curve if coming from Spring Boot

### When to use Micronaut

Micronaut is an excellent choice for:

- REST APIs
- Cloud-native applications
- Microservices
- Serverless applications
- Event-driven applications
- Containerized applications
- GraalVM Native Image applications
- High-performance backend services
- Applications requiring fast startup
- Applications running in Kubernetes

### When to avoid Micronaut

Micronaut may not be the best choice when:

- Your organization is heavily invested in the Spring ecosystem.
- Your project depends on many Spring-specific libraries.
- You require a large ecosystem of third-party integrations.
- Your team has no interest in learning another Java framework.

### Why is Micronaut blazing fast?

When people say Micronaut is **fast**, they primarily mean:

- Faster application startup
- Lower memory consumption
- Faster dependency injection
- Less runtime processing
- Better cold-start performance for serverless applications

Micronaut achieves this by performing most framework work during **compilation** instead of application startup.

Instead of discovering beans and processing annotations at runtime, Micronaut generates the required metadata during compilation.

As a result:

- Less reflection
- Less classpath scanning
- Faster bean creation
- Smaller runtime overhead
- Better GraalVM compatibility

---

# Cloud Native

Cloud-native applications are designed to run efficiently on cloud platforms and container orchestration systems such as Kubernetes.

Typical characteristics include:

- Stateless services
- Independent deployment
- Horizontal scalability
- Container friendly
- Externalized configuration
- High availability
- Easy scaling

### How Micronaut supports Cloud Native

- Lightweight runtime
- Fast startup
- Low memory footprint
- Docker friendly
- Kubernetes ready
- GraalVM Native Image support
- External configuration support
- Health endpoints
- Metrics support
- Distributed tracing integration

---

# Serverless

Serverless applications execute only when needed without managing servers.

The cloud provider automatically provisions infrastructure, scales the application, and charges based on actual usage.

### How Micronaut supports Serverless

Micronaut provides integrations for:

- AWS Lambda
- Azure Functions
- Google Cloud Functions
- Oracle Cloud Functions

Because Micronaut starts very quickly and uses less memory, it significantly reduces serverless cold-start latency.

---

# GraalVM Native Image

### What is GraalVM?

GraalVM is a high-performance JDK that can compile Java applications into native executables.

Unlike a traditional JAR, a native executable starts directly without requiring a JVM at runtime.

### Benefits

- Extremely fast startup
- Lower memory usage
- Smaller container images
- Better serverless cold starts
- Faster scaling
- Improved cloud efficiency

This project is fully compatible with GraalVM Native Image.

---

# Micronaut Data

One of the main reasons for choosing Micronaut for this project is **Micronaut Data**.

This project uses:

**micronaut-data-hibernate-jpa**

Micronaut Data is Micronaut's data access framework that provides compile-time repository generation while remaining compatible with JPA and Hibernate.

Instead of generating repository implementations at runtime, Micronaut generates them during compilation.

### Benefits

- Faster application startup
- Lower memory usage
- Compile-time repository generation
- Less reflection
- Cleaner repository code
- Built-in pagination support
- Built-in sorting support
- JPQL support
- Criteria API support
- Hibernate JPA integration
- JDBC support
- R2DBC support
- MongoDB support

This project demonstrates:

- Micronaut Data Hibernate JPA
- Custom JPQL queries
- Entity joins
- Pageable
- Pagination
- Sorting
- Dynamic filtering
- Compile-time generated repositories

> **Note**
>
> Newer Micronaut Data versions provide a significantly improved Criteria API. However, versions newer than **4.10.17** require **Java 25 or later**. This project targets **Java 21**, so Micronaut **4.10.17** has been used.

---

# Event-Driven Applications

Event-driven applications communicate by publishing and consuming events instead of direct synchronous calls.

Micronaut supports both in-process application events and distributed messaging systems.

### Supported messaging technologies

- Apache Kafka
- RabbitMQ
- Apache Pulsar
- MQTT
- JMS

Micronaut also provides **Application Events**, which allow different components within the same application to communicate without requiring an external message broker.

---

# Reactive Programming

Micronaut uses the **Netty** HTTP server by default.

Benefits include:

- Non-blocking request handling
- High concurrency
- Efficient resource utilization
- Excellent scalability

For typical REST APIs like this project, no additional reactive configuration is required.

Micronaut also supports:

- Project Reactor
- RxJava
- CompletableFuture
- Reactive Streams

---

# Languages Supported

Micronaut officially supports:

- Java
- Kotlin
- Groovy

This project is implemented using **Java 21**.

---

# JAX-RS Support

Micronaut does **not** use RESTEasy, Jersey, or Apache CXF as its REST implementation.

Instead, it provides its own high-performance HTTP framework built on top of **Netty**.

The programming model is very similar to JAX-RS, making it easy for Java developers to learn.

### Common REST Annotations

| Annotation | Description |
|------------|-------------|
| `@Controller` | Defines a REST controller. |
| `@Get` | Handles HTTP GET requests. |
| `@Post` | Handles HTTP POST requests. |
| `@Put` | Handles HTTP PUT requests. |
| `@Delete` | Handles HTTP DELETE requests. |
| `@Patch` | Handles HTTP PATCH requests. |
| `@Body` | Maps the request body. |
| `@PathVariable` | Reads a value from the URL path. |
| `@QueryValue` | Reads query parameters. |
| `@Header` | Reads HTTP headers. |
| `@CookieValue` | Reads cookie values. |
| `@Produces` | Specifies the response content type. |
| `@Consumes` | Specifies the request content type. |
| `@Status` | Returns a specific HTTP status code. |
| `@Error` | Handles application exceptions. |

---

# Creating a New Micronaut Project

Micronaut provides an official project generator that creates a fully configured project skeleton.
- https://micronaut.io/launch/

You can choose:

- Java, Kotlin or Groovy
- Maven or Gradle
- Java version
- Micronaut version
- Required features
- Build plugins

The generated project is production-ready and includes the recommended project structure.

---

# How to Run this Project

This section explains how to run the application during development as well as the different deployment options supported by Micronaut.

### Prerequisites

Install the following software before running the project:

- Java 21
- Maven 3.9+
- PostgreSQL
- Git
- Docker (Optional)
- GraalVM 21 (Optional - for Native Image)

### Step 1 - Clone the Project

```bash
git clone https://github.com/SirajChaudhary/micronaut-cloudnative-microservice.git
```

```
cd micronaut-cloudnative-microservice
cd healthcare-appointment-service
```

### Step 2 - Create and Configure PostgreSQL Database

Create the database schema, table and load sample data with the following SQL scripts:

```text
1. <project>/sql/healthcare-appointment-schema.sql
2. <project>/sql/healthcare-appointment-data.sql
```

Then configure your PostgreSQL connection details in:

```text
src/main/resources/application.yml
```

### Step 3 - Run the Application

You can run the application in several ways.

- From your IDE
- Using Maven
- Running the JVM JAR
- Running a Docker container
- Running a GraalVM Native Executable
- Running a GraalVM Native Docker container

### 👉 Run During Development

Start the application using:

```bash
mvn mn:run
```

To stop the application, press:

```text
Ctrl + C
```

After making code changes, simply run the application again:

```bash
mvn mn:run
```

Once development is complete, build the application for deployment. Micronaut supports multiple deployment options, including a traditional **JVM JAR**, a **Docker image**, a **GraalVM Native Executable**, and a **GraalVM Native Docker container**. For example, generate a fresh GraalVM Native Executable:

```bash
mvn clean native:compile
```

---

# Access Swagger UI

After the application starts, open:

```text
http://localhost:8080/swagger-ui
```

---

# Deployment Options

Micronaut supports multiple deployment models.

- JVM JAR
- Docker Container
- GraalVM Native Executable
- GraalVM Native Docker Container

# Option 1 - JVM JAR

This is the traditional Java deployment.

### Step 1 - Build

```bash
mvn clean package
```

This generates:

```text
target/healthcare-appointment-service-<version>.jar
```

### Step 2 - Run

```bash
java -jar target/healthcare-appointment-service-<version>.jar
```

### Step 3 - Verify

Open:

```text
http://localhost:8080/swagger-ui
```

# Option 2 - Docker

The application can be packaged as a Docker container.

In this setup, PostgreSQL continues running on your local machine.

### Step 1 - Dockerfile

Create a file named **Dockerfile**.

```dockerfile
# Build Stage
FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

# Runtime Stage
FROM eclipse-temurin:21-jre

WORKDIR /app

COPY --from=builder /app/target/healthcare-appointment-service-*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
```

### Step 2 - Build Docker Image

```bash
docker build -t healthcare-appointment-service .
```

### Step 3 - Run Docker Container

**Windows / macOS**

```bash
docker run \
-p 2026:8080 \
-e DATASOURCES_DEFAULT_URL=jdbc:postgresql://host.docker.internal:5432/healthcaredb \
-e DATASOURCES_DEFAULT_USERNAME=postgres \
-e DATASOURCES_DEFAULT_PASSWORD=siraj123 \
healthcare-appointment-service
```

`host.docker.internal` allows the container to connect to PostgreSQL running on your local machine.

### Step 4 - Verify

Open:

```text
http://localhost:2026/swagger-ui
```

# Option 3 - GraalVM Native Executable

Micronaut provides excellent support for GraalVM Native Image.

Compared to a traditional JVM application, a native executable typically offers:

- Much faster startup
- Lower memory usage
- Smaller deployment footprint
- Better cloud-native performance
- Better serverless cold starts

### Step 1 - Install GraalVM

On macOS:

```bash
brew install graalvm/tap/graalvm-community@21
```

Configure your shell:

```bash
echo 'export JAVA_HOME=$(/usr/libexec/java_home -v21)' >> ~/.zshrc

echo 'export PATH=$JAVA_HOME/bin:$PATH' >> ~/.zshrc

source ~/.zshrc
```

### Step 2 - Verify Installation

```bash
java -version
```

```bash
native-image --version
```

The output should show:

- GraalVM
- Native Image

### Step 3 - Build Native Executable

```bash
mvn clean native:compile
```

Alternatively:

```bash
mvn clean package -Dpackaging=native-image
```

This generates a platform-specific native executable under:

```text
target/
```

### Step 4 - Run Native Executable

macOS / Linux

```bash
./target/healthcare-appointment-service
```

Windows

```text
target\healthcare-appointment-service.exe
```

### Step 5 - Verify

Open:

```text
http://localhost:8080/swagger-ui
```

### Development Workflow

During development, you normally don't rebuild the native executable after every code change.

Use:

```bash
mvn mn:run
```

Once development is complete, generate a fresh native executable.

```bash
mvn clean native:compile
```

---

# Option 4 - GraalVM Native Docker Container

Instead of packaging a JVM application, Micronaut can package a native executable inside Docker.

Benefits:

- Extremely fast startup
- Lower memory usage
- Smaller container images
- Excellent Kubernetes deployments
- Excellent serverless deployments

### Step 1 - Build Native Docker Image

```bash
mvn package -Dpackaging=docker-native
```

### Step 2 - Run Container

```bash
docker run -p 8080:8080 healthcare-appointment-service
```

### Step 3 - Verify

Open:

```text
http://localhost:8080/swagger-ui
```

---

# REST APIs

The application exposes RESTful APIs for managing hospitals, doctors, patients, and appointments.

Each module provides standard CRUD operations, while the Doctor module additionally provides an advanced search API supporting partial search, filtering, pagination, and sorting.

<br />

## Hospital APIs

Provides CRUD APIs to manage hospitals.

**CRUD APIs**

| Method | Endpoint | Description | Example URL |
|---------|----------|-------------|-------------|
| POST | `/hospitals` | Create a new hospital. | `POST /hospitals` |
| GET | `/hospitals/{hospitalCode}` | Retrieve a hospital by hospital code. | `GET /hospitals/HOS-000001` |
| PUT | `/hospitals/{hospitalCode}` | Update hospital details. | `PUT /hospitals/HOS-000001` |
| DELETE | `/hospitals/{hospitalCode}` | Delete a hospital. | `DELETE /hospitals/HOS-000001` |
| GET | `/hospitals` | Retrieve all hospitals with pagination and sorting. | `GET /hospitals?page=0&size=10&sort=hospitalName,asc` |

<br />

## Doctor APIs

Provides CRUD APIs to manage doctors and an advanced search API for searching doctors using multiple search criteria.

**CRUD APIs**

| Method | Endpoint | Description | Example URL |
|---------|----------|-------------|-------------|
| POST | `/doctors` | Create a new doctor. | `POST /doctors` |
| GET | `/doctors/{doctorCode}` | Retrieve a doctor by doctor code. | `GET /doctors/DOC-000001` |
| PUT | `/doctors/{doctorCode}` | Update doctor details. | `PUT /doctors/DOC-000001` |
| DELETE | `/doctors/{doctorCode}` | Delete a doctor. | `DELETE /doctors/DOC-000001` |
| GET | `/doctors` | Retrieve all doctors with pagination and sorting. | `GET /doctors?page=0&size=10&sort=firstName,asc` |

### Search API

Endpoint

```http
GET /doctors/search
```

The Doctor Search API demonstrates one of the key features of this project.

It is implemented using:

- Micronaut Data JPA
- Custom JPQL Query
- Entity JOIN
- Compile-time Repository Generation
- Dynamic Filtering
- Case-insensitive Partial Search
- Pageable Pagination
- Pageable Sorting
- Separate Count Query for Pagination

A single endpoint supports keyword search, filtering, pagination, sorting, and combinations of all these features.

### Partial Search

The **q** parameter performs a **case-insensitive partial search** across multiple doctor fields.

The search is performed on:

- firstName
- lastName
- specialization
- email

The service automatically converts the keyword into **`%keyword%`**, allowing matches anywhere within the text.

Examples

| Search | Example URL |
|---------|-------------|
| Search by doctor's first name | `GET /doctors/search?q=amit&page=0&size=10` |
| Search by last name | `GET /doctors/search?q=sharma&page=0&size=10` |
| Search by specialization | `GET /doctors/search?q=cardio&page=0&size=10` |
| Search by email | `GET /doctors/search?q=hospital.com&page=0&size=10` |

### Exact Filters

Filters allow you to narrow the search results using one or more search criteria.

All filters are optional and can be combined.

| Filter | Description | Example URL |
|---------|-------------|-------------|
| hospitalCode | Returns doctors belonging to a hospital. | `GET /doctors/search?hospitalCode=HOS-000001&page=0&size=10` |
| specialization | Case-insensitive specialization filter. | `GET /doctors/search?specialization=Neurology&page=0&size=10` |
| minExperience | Returns doctors having experience greater than or equal to the specified value. | `GET /doctors/search?minExperience=5&page=0&size=10` |
| minRating | Returns doctors having rating greater than or equal to the specified value. | `GET /doctors/search?minRating=4.2&page=0&size=10` |
| active | Returns active or inactive doctors. | `GET /doctors/search?active=true&page=0&size=10` |

### Combine Search and Filters

Multiple search criteria can be combined within a single request.

| Description | Example URL |
|-------------|-------------|
| Search cardiologists in a specific hospital with minimum experience and rating | `GET /doctors/search?q=cardio&hospitalCode=HOS-000001&minExperience=5&minRating=4.0&page=0&size=10` |
| Search active doctors by specialization | `GET /doctors/search?specialization=General%20Medicine&active=true&page=0&size=10` |
| Search doctors by name with minimum experience | `GET /doctors/search?q=amit&minExperience=10&page=0&size=10` |
| Search doctors using multiple filters | `GET /doctors/search?hospitalCode=HOS-000001&specialization=Cardiology&minRating=4.5&active=true&page=0&size=10` |

### Pagination

Pagination retrieves large result sets page by page.

Micronaut Data automatically handles pagination using **Pageable**.

| Parameter | Description |
|-----------|-------------|
| page | Zero-based page index. |
| size | Number of records per page. |

Examples

| Description | Example URL |
|-------------|-------------|
| First page | `GET /doctors/search?page=0&size=10` |
| Second page | `GET /doctors/search?page=1&size=10` |
| Third page with 20 records | `GET /doctors/search?page=2&size=20` |
| Pagination with keyword search | `GET /doctors/search?q=cardio&page=1&size=20` |

### Sorting

Sorting orders the search results by one or more fields.

Micronaut Data automatically applies sorting through **Pageable**.

Supported Properties

- doctorCode
- firstName
- lastName
- specialization
- experience
- consultationFee
- email
- phoneNumber
- rating
- active
- createdAt
- updatedAt

Examples

| Description | Example URL |
|-------------|-------------|
| Sort by rating (descending) | `GET /doctors/search?sort=rating,desc&page=0&size=10` |
| Sort by experience (ascending) | `GET /doctors/search?sort=experience,asc&page=0&size=10` |
| Sort by consultation fee | `GET /doctors/search?sort=consultationFee,desc&page=0&size=10` |
| Multi-column sorting | `GET /doctors/search?sort=lastName,asc&sort=firstName,asc&page=0&size=10` |
| Sort with filters | `GET /doctors/search?hospitalCode=HOS-000001&minExperience=5&sort=experience,desc&page=0&size=20` |

### Useful Search Examples

| Description | Example URL |
|-------------|-------------|
| Find cardiologists with high ratings | `GET /doctors/search?q=cardio&minRating=4.5&sort=rating,desc&page=0&size=10` |
| Find experienced neurologists | `GET /doctors/search?specialization=Neurology&minExperience=10&page=0&size=10` |
| Search by email domain | `GET /doctors/search?q=hospital.com&page=0&size=10` |
| Find active doctors ordered by latest update | `GET /doctors/search?active=true&sort=updatedAt,desc&page=0&size=10` |
| Find inactive doctors | `GET /doctors/search?active=false&page=0&size=10` |
| Search doctors in a hospital | `GET /doctors/search?hospitalCode=HOS-000001&page=0&size=10` |
| Search doctors by specialization and rating | `GET /doctors/search?specialization=Cardiology&minRating=4.8&page=0&size=10` |
<br />
<img width="3196" height="1894" alt="image" src="https://github.com/user-attachments/assets/013458b4-29e4-4118-b7a8-e382af0512b3" />
<br /><br />
<img width="3196" height="1902" alt="image" src="https://github.com/user-attachments/assets/c1d871c7-e8ad-441c-ac4f-fa2cb09f6ab6" />
<br /><br />
<img width="3206" height="1876" alt="image" src="https://github.com/user-attachments/assets/46d97423-03d4-46c9-a9f3-99acdd358d72" />
<br /><br />
<img width="3226" height="1898" alt="image" src="https://github.com/user-attachments/assets/dd729240-4629-4bc9-b61e-7a69c887fa47" />
<br /><br />
<img width="3212" height="1548" alt="image" src="https://github.com/user-attachments/assets/97bb2fe0-1ac2-4bb5-b008-c84b9e5a1205" />
<br /><br />
<img width="3226" height="1894" alt="image" src="https://github.com/user-attachments/assets/111ddb0b-1d6c-4a2d-8f86-733f254ade3c" />
<br /><br />
<img width="3222" height="1894" alt="image" src="https://github.com/user-attachments/assets/a1a7905b-ba78-42d9-8333-ddd3a8822f40" />

### Notes

- The **q** parameter enables case-insensitive partial search.
- Omitting **q** performs filter-only searches.
- All filters are optional.
- Multiple filters can be combined in a single request.
- Pagination is handled automatically using **Pageable**.
- Sorting is handled automatically using **Pageable**.
- If the sort direction is omitted, **ascending** order is used by default.
- A separate count query is executed to efficiently calculate total pages.

<br />

## Patient APIs

Provides CRUD APIs to manage patients.

### CRUD APIs

| Method | Endpoint | Description | Example URL |
|---------|----------|-------------|-------------|
| POST | `/patients` | Register a new patient. | `POST /patients` |
| GET | `/patients/{patientCode}` | Retrieve a patient by patient code. | `GET /patients/PAT-000001` |
| PUT | `/patients/{patientCode}` | Update patient details. | `PUT /patients/PAT-000001` |
| DELETE | `/patients/{patientCode}` | Delete a patient. | `DELETE /patients/PAT-000001` |
| GET | `/patients` | Retrieve all patients with pagination and sorting. | `GET /patients?page=0&size=10&sort=firstName,asc` |

<br />

## Appointment APIs

Provides CRUD APIs to manage appointments.

### CRUD APIs

| Method | Endpoint | Description | Example URL |
|---------|----------|-------------|-------------|
| POST | `/appointments` | Create a new appointment. | `POST /appointments` |
| GET | `/appointments/{appointmentCode}` | Retrieve an appointment by appointment code. | `GET /appointments/APT-000001` |
| PUT | `/appointments/{appointmentCode}` | Update appointment details. | `PUT /appointments/APT-000001` |
| DELETE | `/appointments/{appointmentCode}` | Cancel an appointment. | `DELETE /appointments/APT-000001` |
| GET | `/appointments` | Retrieve all appointments with pagination and sorting. | `GET /appointments?page=0&size=10&sort=appointmentDate,asc` |

---

# Performance Comparison

| Feature | JVM JAR | GraalVM Native |
|----------|----------|----------------|
| Startup Time | Good | Excellent |
| Memory Usage | Moderate | Low |
| Cold Start | Good | Excellent |
| Container Size | Larger | Smaller |
| Serverless | Good | Excellent |
| Cloud Native | Good | Excellent |

---

# Micronaut vs Spring Boot

| Feature | Micronaut | Spring Boot |
|---------|-----------|-------------|
| Initial Release | 2018 | 2014 |
| Startup Time | Faster | Slower |
| Memory Usage | Lower | Higher |
| Dependency Injection | Compile-time | Runtime |
| Reflection | Minimal | Extensive |
| GraalVM Support | Excellent | Good |
| Native Image | First-class support | Supported |
| Cloud Native | Excellent | Excellent |
| Serverless | Excellent | Good |
| Learning Curve | Moderate | Easy |
| Ecosystem | Smaller | Very Large |

---

# Micronaut vs Quarkus

| Feature | Micronaut | Quarkus |
|---------|-----------|----------|
| Initial Release | 2018 | 2019 |
| Primary Focus | Cloud-native applications | Kubernetes & GraalVM |
| Startup Time | Excellent | Excellent |
| Memory Usage | Very Low | Very Low |
| Compile-time DI | Yes | Yes |
| GraalVM Support | Excellent | Excellent |
| Native Image | First-class support | First-class support |
| Serverless | Excellent | Excellent |
| Reactive Support | Yes | Yes |
| Learning Curve | Moderate | Moderate |

---

# Official Documentation

Micronaut provides excellent official documentation, guides, and tutorials for getting started as well as advanced topics.

The official guides cover:

- REST APIs
- Micronaut Data
- Security
- Serverless
- Kafka
- RabbitMQ
- GraalVM
- Docker
- Kubernetes
- Testing
- Cloud deployments

These resources are highly recommended for learning Micronaut and following best practices.

---

# Future Enhancements

This project is intentionally kept simple to demonstrate Micronaut concepts and best practices. However, it can be extended into a full-featured healthcare platform similar to **Practo** by adding the following features:

Patient Features

- Patient registration and profile management
- Patient login and authentication
- Medical history management
- Prescription history
- Lab reports and medical records
- Appointment history
- Online appointment booking
- Appointment cancellation and rescheduling
- Favorite doctors
- Patient notifications

Doctor Features

- Doctor registration and verification
- Doctor profile management
- Availability and schedule management
- Consultation fee management
- Online consultation support
- Doctor dashboard
- Patient history access
- Prescription generation
- Doctor reviews and ratings

Hospital Features

- Hospital profile management
- Department management
- Doctor assignment
- Staff management
- Bed availability
- Hospital working hours
- Branch management

Appointment Features

- Real-time appointment booking
- Appointment reminders
- Appointment status tracking
- Waiting list management
- Calendar integration
- Video consultation support
- Follow-up appointment scheduling

Search and Discovery

- Search doctors by specialty
- Search doctors by hospital
- Search doctors by location
- Search using multiple filters
- Nearby hospital search
- Doctor recommendations
- Trending specialists
- Popular hospitals

Reviews and Ratings

- Patient reviews
- Doctor ratings
- Hospital ratings
- Verified patient feedback
- Review moderation

Payments

- Online payment integration
- Multiple payment methods
- Consultation invoices
- Refund management
- Payment history

Notifications

- Email notifications
- SMS notifications
- Push notifications
- Appointment reminders
- Prescription reminders

Security

- JWT authentication
- OAuth2 / OpenID Connect
- Role-based access control
- Multi-factor authentication
- Audit logging
- API rate limiting

Cloud-Native Features

- API Gateway
- Service Discovery
- Distributed Caching
- Event-Driven Architecture
- Kafka Integration
- Redis Integration
- Distributed Tracing
- Centralized Logging
- Metrics and Monitoring
- Kubernetes Deployment
- CI/CD Pipeline

AI-Powered Features

- AI-based doctor recommendations
- Symptom checker
- Intelligent appointment scheduling
- Medical report summarization
- Prescription recommendations
- Health risk prediction
- Chatbot for patient assistance

Mobile Support

- Android application
- iOS application
- Responsive web application
- Push notification support

Third-Party Integrations

- Google Maps
- Payment Gateway
- SMS Gateway
- Email Service
- Video Calling Platform
- Electronic Health Record (EHR) Systems
- Insurance Providers

Overall Vision

With these enhancements, the application can evolve from a Micronaut demonstration project into a scalable healthcare platform that enables patients to discover doctors, book appointments, manage medical records, consult online, make secure payments, and access healthcare services through a single platform.

---

# References

- Micronaut Documentation  
  https://docs.micronaut.io/

- Micronaut Guides  
  https://guides.micronaut.io/latest/index.html

- Micronaut Launch  
  https://micronaut.io/launch/

- Micronaut Data  
  https://micronaut-projects.github.io/micronaut-data/latest/guide/

- GraalVM Documentation  
  https://www.graalvm.org/latest/docs/

- Micronaut Tutorial

  https://www.youtube.com/watch?v=KIp9PlyJOjg

---

# License

Free software, [Siraj Chaudhary](https://www.linkedin.com/in/sirajchaudhary/)
