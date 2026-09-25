<div align="center">

# 🎮 Ludex

**A Game Library & Favorites Management REST API**

*Built with Spring Boot · Java 21 · PostgreSQL*

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen?style=for-the-badge&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-blue?style=for-the-badge&logo=postgresql)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow?style=for-the-badge)

</div>

---

## 📖 What is Ludex?

**Ludex** is a backend REST API that lets you manage a personal game library. You can add games, browse all games, fetch a game by ID, mark your favorites, and remove games — all through simple HTTP requests.

Think of it as your personal **game catalog with a favorites shelf**.

> **Current Stage:** Active development — core CRUD, input validation, error handling, and favorites are fully working. Favorites persistence and several production-ready features are still being built.

---

## 📋 Table of Contents

1. [Architecture Overview](#-architecture-overview)
2. [Project Structure](#-project-structure)
3. [Tech Stack](#-tech-stack)
4. [API Reference](#-api-reference)
5. [Data Model](#-data-model)
6. [Getting Started (Local Setup)](#-getting-started-local-setup)
7. [Deployment Guide](#-deployment-guide)
8. [Current Condition & Known Issues](#️-current-condition--known-issues)
9. [Future Roadmap](#-future-roadmap)
10. [Contributing](#-contributing)

---

## 🏗 Architecture Overview

Ludex follows a classic **3-Layer Spring Boot architecture** with a dedicated exception handling layer:

```
┌─────────────────────────────────────────────────────────┐
│                    CLIENT (Browser / Postman)            │
└──────────────────────────┬──────────────────────────────┘
                           │  HTTP Request
                           ▼
┌─────────────────────────────────────────────────────────┐
│                  CONTROLLER LAYER                        │
│   gamecontroller.java  │  healthcontroller.java         │
│   • Receives HTTP requests                              │
│   • Validates input (@Valid), delegates to Services     │
│   • Returns HTTP responses                              │
└──────────────────────────┬──────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────┐
│                   SERVICE LAYER                          │
│   gameservice.java  │  favoritegameservice.java         │
│   • Contains all business logic                         │
│   • Manages game operations & favorites list            │
│   • ⚠️  Favorites currently stored in-memory            │
└──────────────────────────┬──────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────┐
│                REPOSITORY LAYER (JPA)                    │
│   GameRepository.java                                   │
│   • Talks directly to the PostgreSQL database           │
│   • Extends JpaRepository (full CRUD out of the box)    │
└──────────────────────────┬──────────────────────────────┘
                           │
                           ▼
┌─────────────────────────────────────────────────────────┐
│                   PostgreSQL DATABASE                    │
│   Table: game                                           │
│   Columns: game_id, game_name, game_description,        │
│            section                                      │
└─────────────────────────────────────────────────────────┘

           ↕  (catches exceptions from any layer)
┌─────────────────────────────────────────────────────────┐
│              EXCEPTION HANDLING LAYER                    │
│   GlobalExceptionHandler.java                           │
│   • @RestControllerAdvice — handles errors globally     │
│   • GameNotFoundException → 404 JSON response           │
│   • MethodArgumentNotValidException → 400 JSON response │
└─────────────────────────────────────────────────────────┘
```

### Request Flow (Example: Get a Game by ID)

```
GET /game/{id}
       │
       ▼
gamecontroller.getgamebyid()   ← extracts path variable
       │
       ▼
gameservice.findgame(id)       ← queries DB, throws GameNotFoundException if missing
       │
       ▼ (if found)                     ▼ (if not found)
Returns 200 OK with game JSON   GlobalExceptionHandler → 404 JSON error
```

---

## 📁 Project Structure

```
ludex/
└── ludex/                           # Spring Boot root
    ├── pom.xml                      # Maven dependencies & build config
    └── src/
        ├── main/
        │   ├── java/com/harshvardhan/ludex/
        │   │   ├── LudexApplication.java           # 🚀 App entry point
        │   │   ├── controller/
        │   │   │   ├── gamecontroller.java          # Game & Favorites API
        │   │   │   └── healthcontroller.java        # Health check endpoint
        │   │   ├── exception/
        │   │   │   ├── GlobalExceptionHandler.java  # 🛡 Global error handler
        │   │   │   ├── GameNotFoundException.java   # Custom 404 exception
        │   │   │   └── ErrorResponse.java           # Structured error body
        │   │   ├── model/
        │   │   │   └── game.java                   # Game entity (DB table)
        │   │   ├── repository/
        │   │   │   └── GameRepository.java          # JPA data access layer
        │   │   └── service/
        │   │       ├── gameservice.java             # Game business logic
        │   │       └── favoritegameservice.java     # Favorites business logic
        │   └── resources/
        │       └── application.properties           # DB & app configuration
        └── test/
            └── java/                               # Unit/integration tests
```

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| **Java** | 21 (LTS) | Core programming language |
| **Spring Boot** | 4.1.1 | Web framework & auto-configuration |
| **Spring Web MVC** | 4.1.1 | REST API endpoints |
| **Spring Data JPA** | 4.1.1 | Database ORM abstraction |
| **Spring Validation** | 4.1.1 | Bean validation (`@Valid`, `@NotBlank`, etc.) |
| **Hibernate** | (bundled) | JPA implementation / SQL generation |
| **PostgreSQL** | Latest | Primary relational database |
| **Lombok** | Latest | Reduces boilerplate (getters/setters) |
| **Spring DevTools** | 4.1.1 | Hot reload during development |
| **Maven** | 3.x | Build & dependency management |

---

## 📡 API Reference

**Base URL:** `http://localhost:8080`

### 🔵 Health Check

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/home` | Checks if the server is running |

**Response:**
```
Hi, this program works!
```

---

### 🟢 Game Management

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/game/allgames` | Get all games in the library |
| `GET` | `/game/{id}` | Get a single game by its ID |
| `POST` | `/game/addgames` | Add a new game |
| `DELETE` | `/game/{id}` | Delete a game by its ID |

#### GET `/game/allgames`
Returns a list of all games stored in the database.

**Sample Response:**
```json
[
  {
    "game_id": 1,
    "game_name": "The Witcher 3",
    "game_description": "An open-world RPG masterpiece.",
    "section": "Home"
  },
  {
    "game_id": 2,
    "game_name": "Hollow Knight",
    "game_description": "A challenging metroidvania.",
    "section": "Favorite"
  }
]
```

#### GET `/game/{id}`
Returns a single game by its ID. Returns a structured `404` error if the game does not exist.

**Sample Response (200 OK):**
```json
{
  "game_id": 1,
  "game_name": "The Witcher 3",
  "game_description": "An open-world RPG masterpiece.",
  "section": "Home"
}
```

**Sample Response (404 Not Found):**
```json
{
  "status": 404,
  "message": "Game with ID 99 not found"
}
```

#### POST `/game/addgames`
Adds a new game to the library. Input is validated — `game_name` and `game_description` are required.

**Request Body:**
```json
{
  "game_name": "Elden Ring",
  "game_description": "A brutal open-world action RPG."
}
```

**Sample Response (200 OK):**
```json
{
  "game_id": 3,
  "game_name": "Elden Ring",
  "game_description": "A brutal open-world action RPG.",
  "section": "Home"
}
```

**Sample Response (400 Bad Request — validation failure):**
```json
{
  "status": 400,
  "message": "game_name must not be blank"
}
```

#### DELETE `/game/{id}`
Deletes a game by its ID.

**Sample Response:**
```
Game is deleted
```
*(Returns `"Failed"` if the ID does not exist)*

---

### ❤️ Favorites Management

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/game/favorites` | Get all favorite games |
| `POST` | `/game/addfavorites/{game_id}` | Add a game to favorites |
| `DELETE` | `/game/delete/{id}` | Remove a game from favorites |

#### POST `/game/addfavorites/{game_id}`
Marks an existing game as a favorite. Its `section` field changes from `"Home"` → `"Favorite"`.

**Sample Response:**
```
Game Added to the favrite
```

#### DELETE `/game/delete/{id}`
Removes a game from favorites. Its `section` reverts back to `"Home"`.

---

## 🗄 Data Model

### `game` Entity / Database Table

| Column | Type | Description |
|---|---|---|
| `game_id` | `INT` (PK, Auto) | Unique identifier |
| `game_name` | `VARCHAR` | Name/title of the game |
| `game_description` | `VARCHAR` | Short description of the game |
| `section` | `VARCHAR` | `"Home"` (default) or `"Favorite"` |

The `section` field dynamically reflects whether the game is in the user's favorites list.

---

## 🚀 Getting Started (Local Setup)

### Prerequisites

Make sure these are installed on your machine:

- [ ] **Java 21** — [Download here](https://adoptium.net/)
- [ ] **Maven 3.x** — [Download here](https://maven.apache.org/download.cgi)
- [ ] **PostgreSQL** — [Download here](https://www.postgresql.org/download/)
- [ ] **Git** — [Download here](https://git-scm.com/)

---

### Step 1 — Clone the Repository

```bash
git clone https://github.com/Harshvardhan210/Ludex.git
cd Ludex/ludex
```

### Step 2 — Create the PostgreSQL Database

Open your PostgreSQL shell or pgAdmin and run:

```sql
CREATE DATABASE ludex;
```

### Step 3 — Configure the Application

Open `src/main/resources/application.properties` and set your database credentials:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/ludex
spring.datasource.username=YOUR_POSTGRES_USERNAME
spring.datasource.password=YOUR_POSTGRES_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

> 💡 `ddl-auto=update` tells Hibernate to auto-create/update tables based on your entity classes. No manual SQL needed!

### Step 4 — Run the Application

```bash
# Using Maven wrapper (recommended)
./mvnw spring-boot:run

# Or on Windows
mvnw.cmd spring-boot:run
```

### Step 5 — Test It

Open your browser or Postman and visit:
```
http://localhost:8080/home
```
You should see: `Hi, this program works!`

---

## ☁️ Deployment Guide

### Option A — Deploy as a JAR (Simplest)

```bash
# 1. Package the application
./mvnw clean package -DskipTests

# 2. The JAR will be in: target/ludex-0.0.1-SNAPSHOT.jar

# 3. Run on any server with Java 21 installed
java -jar target/ludex-0.0.1-SNAPSHOT.jar
```

Set environment variables on the server instead of hardcoding credentials:

```bash
java -jar ludex-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:postgresql://<DB_HOST>:5432/ludex \
  --spring.datasource.username=<USER> \
  --spring.datasource.password=<PASSWORD>
```

---

### Option B — Deploy with Docker (Recommended for Production)

> ⚠️ A `Dockerfile` does not yet exist in the repo. Here's a template to create one:

**`Dockerfile`**
```dockerfile
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app
COPY . .
RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/ludex-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

**`docker-compose.yml`**
```yaml
version: '3.8'
services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/ludex
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: password
    depends_on:
      - db

  db:
    image: postgres:16
    environment:
      POSTGRES_DB: ludex
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
    volumes:
      - pgdata:/var/lib/postgresql/data

volumes:
  pgdata:
```

```bash
docker-compose up --build
```

---

### Option C — Deploy to a Cloud Platform

| Platform | Steps |
|---|---|
| **Render** | Connect GitHub repo → Select "Web Service" → Set env vars → Deploy |
| **Railway** | Import from GitHub → Add PostgreSQL plugin → Set env vars → Deploy |
| **Heroku** | `heroku create` → Add `heroku-postgresql` addon → `git push heroku main` |
| **AWS EC2** | Launch instance → Install Java 21 → Upload JAR → Run as systemd service |

---

## ⚠️ Current Condition & Known Issues

Here's an honest snapshot of the project's current state:

### ✅ What's Working

| Feature | Status | Notes |
|---|---|---|
| Add a game | ✅ Working | Persisted to PostgreSQL |
| Get all games | ✅ Working | Returns full list from DB |
| Get game by ID | ✅ Working | Returns 404 JSON error if not found |
| Delete a game | ✅ Working | Checks if ID exists first |
| Add to favorites | ✅ Working | Changes `section` to `"Favorite"` |
| View favorites | ✅ Working | Returns in-memory list |
| Remove from favorites | ✅ Working | Reverts `section` to `"Home"` |
| Health check endpoint | ✅ Working | `/home` returns confirmation |
| Input validation | ✅ Working | Returns `400` with message on invalid input |
| Global error handling | ✅ Working | Structured JSON errors for 404 & 400 |

### 🚩 Known Issues & Limitations

| Issue | Impact | Description |
|---|---|---|
| **Favorites not persisted** | 🔴 High | Favorites are stored in an `ArrayList` in memory. Restarting the server **wipes all favorites**. |
| **No authentication** | 🔴 High | All API endpoints are public — anyone can add/delete games. |
| **Naming conventions** | 🟡 Low | Class names like `game`, `gamecontroller` use lowercase, against Java conventions. |
| **No pagination** | 🟡 Low | `getAllGames()` returns the entire table with no limit. |
| **No tests** | 🟠 Medium | The `test/` directory exists but no tests are written yet. |
| **Typo in response** | 🟡 Low | `"Game Added to the favrite"` (typo: "favrite") in the add-favorite response message. |

---

## 🗺 Future Roadmap

The following features are planned for future versions of Ludex:

### 🔐 Security & Authentication
- Integrate **Spring Security** with **JWT (JSON Web Tokens)**
- User registration and login (`POST /auth/register`, `POST /auth/login`)
- Role-based access control: `USER` role for browsing, `ADMIN` role for adding/deleting
- Password hashing with **BCrypt**
- Token refresh mechanism

### 👤 User Management
- Each user will have their own game library and favorites
- User profiles with preferences
- Multiple users can coexist without data conflicts

### ⚡ Caching with Redis
- Cache frequently requested data (e.g., `GET /game/allgames`) using **Redis**
- Drastically reduce database load for read-heavy operations
- Set TTL (Time-To-Live) for cache invalidation
- Session store for authenticated users

### 🧩 Microservices Architecture
Break the monolith into independent services:

```
┌──────────────┐    ┌──────────────┐    ┌──────────────┐
│   Game       │    │  Favorites   │    │    User      │
│   Service    │    │   Service    │    │   Service    │
│  :8081       │    │   :8082      │    │   :8083      │
└──────┬───────┘    └──────┬───────┘    └──────┬───────┘
       │                   │                   │
       └───────────────────┼───────────────────┘
                           │
                    ┌──────▼───────┐
                    │  API Gateway  │
                    │   (Nginx /   │
                    │  Spring GW)  │
                    └──────────────┘
```

- Each service has its own database
- Services communicate via **REST** or **Message Queues (RabbitMQ / Kafka)**
- **API Gateway** as single entry point

### 🐳 Docker & Container Orchestration
- Full `Dockerfile` for each service
- `docker-compose.yml` for local multi-service development
- **Kubernetes** deployment manifests for production clustering
- Horizontal pod autoscaling based on traffic

### 🔄 CI/CD Pipeline
Automate build, test, and deployment with **GitHub Actions**:

```yaml
# Planned pipeline stages:
1. Code Push to GitHub
2. Run Unit & Integration Tests (Maven)
3. Build Docker Image
4. Push Image to DockerHub / AWS ECR
5. Deploy to Staging Environment
6. Run Smoke Tests
7. Deploy to Production (on approval)
```

### 📊 Monitoring & Observability
- **Spring Boot Actuator** for health, metrics, and info endpoints
- **Prometheus** for metrics collection
- **Grafana** dashboards for visualization
- Structured logging with **Logback** / **ELK Stack** (Elastic, Logstash, Kibana)
- Distributed tracing with **Zipkin / Jaeger**

### 📝 API Documentation
- **Swagger / OpenAPI 3.0** integration for interactive API docs
- Auto-generated docs available at `/swagger-ui.html`

### 🧪 Testing
- **Unit Tests** for all service methods (JUnit 5 + Mockito)
- **Integration Tests** for full request-response cycles
- **Test Containers** for spinning up a real PostgreSQL instance during tests

### 🌐 Frontend (Planned)
- A lightweight web UI built with **React** or **Next.js**
- Browse, search, and manage games visually
- A beautiful favorites shelf view

---

## 🤝 Contributing

Contributions are welcome!

1. Fork the repository
2. Create a new feature branch: `git checkout -b feature/your-feature-name`
3. Make your changes and commit: `git commit -m "feat: add your feature"`
4. Push to your fork: `git push origin feature/your-feature-name`
5. Open a **Pull Request** on GitHub

---

## 👨‍💻 Author

**Harshvardhan** — [@Harshvardhan210](https://github.com/Harshvardhan210)

---

<div align="center">

**⭐ Star this repo if you like it! It helps a lot.**

*Made with ❤️ and Java*

</div>
