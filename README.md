# URL Shortener Service

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
