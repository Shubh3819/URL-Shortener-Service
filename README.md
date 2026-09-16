# URL Shortener Service

<<<<<<< HEAD
Spring Boot URL shortener using Java 21, MySQL, Redis and REST APIs.

## Quick Start

### Option A: Docker for MySQL + Redis

Run:

```bash
docker compose up -d
```

The compose file creates:
- MySQL on port 3306
- Redis on port 6379
- Database `url_shortener`
- MySQL root password `root`

Then set these values in `src/main/resources/application.properties`:

```properties
spring.datasource.username=root
spring.datasource.password=root
```

Run the application:

```bash
mvn spring-boot:run
```

### API

Create:
```http
POST http://localhost:8080/api/v1/url
Content-Type: application/json

{
  "url": "https://www.google.com"
}
```

Redirect:
```http
GET http://localhost:8080/{shortCode}
```

Information:
```http
GET http://localhost:8080/api/v1/url/{shortCode}
```
=======
A scalable URL Shortener built using **Java, Spring Boot, MySQL, Redis, and REST APIs** that converts long URLs into compact, shareable links while providing fast redirection and efficient lookup through caching.

## Features

- Shorten long URLs into unique Base62 encoded links
- Fast URL redirection
- RESTful API architecture
- Redis caching for low-latency lookups
- MySQL persistent storage
- Input validation and exception handling
- Modular layered architecture
- Scalable backend design
- Clean and maintainable codebase

---

## Tech Stack

### Backend
- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA

### Database
- MySQL

### Cache
- Redis

### Build Tool
- Maven

### Tools
- Postman
- Git
- Docker (Optional)

---

## System Architecture

```
                Client
                   │
                   ▼
            Spring Boot REST API
                   │
        ┌──────────┴──────────┐
        ▼                     ▼
     Redis Cache          MySQL Database
        │                     │
        └──────────┬──────────┘
                   ▼
            URL Redirection
```

---

## API Endpoints

### Create Short URL

```
POST /api/v1/url
```

Request

```json
{
  "url": "https://www.example.com/very/long/url"
}
```

Response

```json
{
  "shortUrl": "http://localhost:8080/Ab12Cd"
}
```

---

### Redirect

```
GET /{shortCode}
```

Automatically redirects to the original URL.

---

### Get URL Information

```
GET /api/v1/url/{shortCode}
```

Response

```json
{
  "originalUrl": "https://www.example.com",
  "shortCode": "Ab12Cd",
  "createdAt": "...",
  "clicks": 42
}
```

---

## Project Structure

```
src
├── controller
├── service
├── repository
├── entity
├── dto
├── config
├── exception
└── util
```

---

## How It Works

1. User submits a long URL.
2. Backend validates the request.
3. A unique Base62 short code is generated.
4. URL is stored in MySQL.
5. Short code is cached in Redis.
6. User receives a shortened URL.
7. Future requests first check Redis.
8. If not found, MySQL is queried and Redis is updated.
9. User is redirected to the original URL.

---

## Performance Optimizations

- Redis caching reduces database hits
- Base62 encoding generates compact URLs
- Indexed database lookups
- Layered architecture for maintainability
- Exception handling for invalid requests
- RESTful API design
- Connection pooling through Spring Boot

---

## Future Improvements

- Custom aliases
- QR Code generation
- Analytics dashboard
- User authentication
- URL expiration
- Rate limiting
- Distributed ID generation
- Click tracking
- Docker Compose deployment
- Kubernetes deployment

---

## Skills Demonstrated

- Java
- Spring Boot
- REST APIs
- Backend Development
- Redis
- MySQL
- Software Architecture
- Caching
- Database Design
- Exception Handling
- API Design
- Object-Oriented Programming
- Clean Code Principles

---
**Subhrangshu Chatterjee**

GitHub: https://github.com/Shubh3819
>>>>>>> 10cd7bcc8cd119b3f99a61677e5bb8e3dc0dab18
