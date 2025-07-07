# Hotel Service

This service is responsible for managing hotels and their rooms in the hotel reservation system.

## Features

- CRUD operations for hotels and rooms
- DTO layer for request/response separation
- Validation using `jakarta.validation`
- Logging with Logback + Filter
- Testcontainers-based integration tests
- User context propagation via `X-User-Id` header
- Global exception handling
- Dockerized with Dockerfile and docker-compose

## API Endpoints

### Hotel Endpoints

- `GET /api/v1/hotels` - List all hotels
- `GET /api/v1/hotels/{id}` - Get hotel by ID
- `POST /api/v1/hotels` - Create a new hotel
- `PUT /api/v1/hotels/{id}` - Update existing hotel
- `DELETE /api/v1/hotels/{id}` - Delete hotel

### Room Endpoints

- `GET /api/v1/rooms/{hotelId}` - List all rooms by hotel ID
- `POST /api/v1/rooms` - Create a new room
- `PUT /api/v1/rooms/{id}` - Update room by ID
- `DELETE /api/v1/rooms/{id}` - Delete room

## Technologies

- Java 21
- Spring Boot 3.4.2
- PostgreSQL (Tested with Testcontainers & Docker)
- JPA/Hibernate
- Maven

## Usage

### Run with Docker Compose

```
docker-compose up --build
```

### Build Locally

```
./mvnw clean package
java -jar target/*.jar
```

## Developer Info

This service is meant to be consumed via API Gateway. Do not expose this service directly to external traffic.