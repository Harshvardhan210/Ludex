<div align="center">

# 🎮 Ludex

**A Game Library, Favorites Management & RAWG API Integration REST Engine**

*Built with Spring Boot 4.1.1 · Java 21 · PostgreSQL · RAWG Video Games API*

![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.1.1-brightgreen?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring%20Security-Enabled-green?style=for-the-badge&logo=springsecurity)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-blue?style=for-the-badge&logo=postgresql)
![RAWG API](https://img.shields.io/badge/RAWG-API%20Integration-purple?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-Build-red?style=for-the-badge&logo=apachemaven)
![Status](https://img.shields.io/badge/Status-Active%20Development-yellow?style=for-the-badge)

</div>

---

## 📖 What is Ludex?

**Ludex** is a robust backend REST API application that allows users to manage a personal game library, organize favorite titles, categorize games by genre and platform, and search live video game data directly via the **RAWG External Video Games Database API**.

Whether you want to discover trending games from RAWG, add custom games to your collection, link specific platforms and developers, or manage your favorites shelf, Ludex provides clean, decoupled, DTO-driven REST endpoints.

> 🚀 **Latest Version Highlights:** Full integration with external RAWG API via `RestTemplate`, custom DTO mapping layers, JPA relational entity modeling (`@ManyToOne`, `@OneToOne`, `@ManyToMany`), Spring Security filter configuration, genre & platform management, and paginated game listings.

---

## 📋 Table of Contents

1. [Architecture Overview](#-architecture-overview)
2. [Project Structure](#-project-structure)
3. [Tech Stack](#-tech-stack)
4. [API Reference](#-api-reference)
   - [Health Check](#-health-check)
   - [RAWG API Integration](#-rawg-api-integration)
   - [Game Management](#-game-management)
   - [Favorites Management](#-favorites-management)
   - [Genre Management](#-genre-management)
   - [Platform Management](#-platform-management)
5. [Data Model & Relational Mappings](#-data-model--relational-mappings)
6. [Getting Started (Local Setup)](#-getting-started-local-setup)
7. [Deployment Guide](#-deployment-guide)
8. [Current Condition & Status](#-current-condition--status)
9. [Future Roadmap](#-future-roadmap)
10. [Author & Contributing](#-author--contributing)

---

## 🏗 Architecture Overview

Ludex follows a clean **Layered Architecture** with strict Separation of Concerns:

```
┌────────────────────────────────────────────────────────────────────────┐
│                      CLIENT (Browser / Postman)                        │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │ HTTP Request
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                     SPRING SECURITY FILTER CHAIN                       │
│                     (SecurityConfig.java)                              │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │ Permitted Requests
                                    ▼
┌────────────────────────────────────────────────────────────────────────┐
│                            CONTROLLER LAYER                            │
│  gamecontroller.java   │ RawgController.java    │ GenreController.java │
│  PlatformController.java │ healthcontroller.java                       │
│  • Validates payloads using DTOs (@Valid)                              │
│  • Maps parameters & delegates to Service Layer                        │
└───────────────────┬────────────────────────────────┬───────────────────┘
                    │                                │
                    ▼                                ▼
┌───────────────────────────────────────┐ ┌──────────────────────────────┐
│             SERVICE LAYER             │ │      RAWG CLIENT LAYER       │
│  gameservice.java                     │ │  RawgClient.java             │
│  favoritegameservice.java             │ │  • RestTemplate & UriBuilder │
│  GenreService.java                    │ │  • Queries api.rawg.io       │
│  platformService.java                 │ └──────────────┬───────────────┘
│  RawgService.java (Maps RAWG DTOs)    │                │
└───────────────────┬───────────────────┘                │
                    │                                    ▼
                    ▼                         ┌──────────────────────────┐
┌───────────────────────────────────────┐     │     EXTERNAL RAWG API    │
│        REPOSITORY LAYER (JPA)         │     │    https://api.rawg.io   │
│  GameRepository  │ GenreRepository    │     └──────────────────────────┘
│  platformRepository │ GameDetailsRepo │
└───────────────────┬───────────────────┘
                    │ SQL Queries
                    ▼
┌───────────────────────────────────────┐
│          PostgreSQL DATABASE          │
│  Tables: game, genre, platform,       │
│          game_details, game_platform  │
└───────────────────────────────────────┘
```

### Key Architectural Concepts:
- **External API Integration:** `RawgClient` uses `RestTemplate` and `UriComponentsBuilder` to query live RAWG game details and search results.
- **DTO Transformation:** Raw responses from RAWG are mapped into decoupled `LudexGameDTO` and `LudexGameDetailsDTO` models to preserve API cleanliness.
- **Relational Domain Model:** Entities feature `@ManyToOne` (Genre), `@OneToOne` (GameDetails), and `@ManyToMany` (Platform) mappings.
- **Global Error Handling:** `GlobalExceptionHandler` intercepts exceptions and formats responses as standardized JSON (`ErrorResponse`).

---

## 📁 Project Structure

```
ludex/
├── pom.xml                               # Maven dependencies & build configuration
└── src/
    ├── main/
    │   ├── java/com/harshvardhan/ludex/
    │   │   ├── LudexApplication.java     # 🚀 Application Entry Point
    │   │   ├── client/
    │   │   │   └── RawgClient.java       # HTTP Client for external RAWG API calls
    │   │   ├── config/
    │   │   │   └── SecurityConfig.java   # Spring Security filter chain configuration
    │   │   ├── controller/
    │   │   │   ├── gamecontroller.java   # CRUD operations for games (DTOs & Pagination)
    │   │   │   ├── RawgController.java   # RAWG API Search & Details endpoints
    │   │   │   ├── GenreController.java  # Genre management endpoints
    │   │   │   ├── PlatformController.java # Platform management endpoints
    │   │   │   └── healthcontroller.java # System health check endpoint
    │   │   ├── dto/
    │   │   │   ├── GameRequestDTO.java   # Incoming game creation/update payload
    │   │   │   ├── GameResponseDTO.java  # Outgoing game summary payload
    │   │   │   ├── GameDetailsDTO.java   # Game details payload
    │   │   │   ├── LudexGameDTO.java     # Processed RAWG game summary
    │   │   │   ├── LudexGameDetailsDTO.java # Processed RAWG detailed game info
    │   │   │   ├── LudexRawgResponseDTO.java # Paginated wrapper for RAWG search
    │   │   │   ├── RawgGameDTO.java      # Raw RAWG result model
    │   │   │   ├── RawgGameDetailsDTO.java # Raw RAWG details model
    │   │   │   └── RawgResponseDTO.java  # Raw RAWG paginated response model
    │   │   ├── exception/
    │   │   │   ├── GlobalExceptionHandler.java # 🛡 Global Exception Handler (@RestControllerAdvice)
    │   │   │   ├── GameNotFoundException.java  # Custom 404 Exception for games
    │   │   │   ├── GenreNotFoundException.java # Custom 404 Exception for genres
    │   │   │   └── ErrorResponse.java    # Standard error response body
    │   │   ├── model/
    │   │   │   ├── game.java             # Core Game Entity
    │   │   │   ├── genre.java            # Genre Entity
    │   │   │   ├── Platform.java         # Gaming Platform Entity
    │   │   │   └── GameDetails.java      # Additional metadata (Developer, Release Year)
    │   │   ├── repository/
    │   │   │   ├── GameRepository.java   # JPA repository for games
    │   │   │   ├── GenreRepository.java  # JPA repository for genres
    │   │   │   ├── platformRepository.java # JPA repository for platforms
    │   │   │   └── GameDetailsRepository.java # JPA repository for game details
    │   │   └── service/
    │   │       ├── gameservice.java      # Game business logic & DTO mapping
    │   │       ├── RawgService.java     # RAWG data fetching & transformation
    │   │       ├── favoritegameservice.java # Favorites management
    │   │       ├── GenreService.java     # Genre business logic
    │   │       └── platformService.java  # Platform business logic
    │   └── resources/
    │       └── application.properties    # PostgreSQL, Hibernate & RAWG API config
    └── test/                            # Unit & Integration test suite
```

---

## 🛠 Tech Stack

| Technology | Version | Purpose |
|---|---|---|
| **Java** | 21 (LTS) | Primary programming language |
| **Spring Boot** | 4.1.1 | Application framework & auto-configuration |
| **Spring Web MVC** | 4.1.1 | RESTful Web Services & REST Controllers |
| **Spring Data JPA** | 4.1.1 | Object-Relational Mapping (ORM) and Repositories |
| **Spring Security** | 4.1.1 | Web Security & HTTP request authorization |
| **Spring Validation** | 4.1.1 | Request body constraint validation (`@Valid`, `@NotBlank`) |
| **PostgreSQL** | Latest | Relational Database Management System |
| **Lombok** | Latest | Reduces boilerplate code (Getters/Setters/Constructors) |
| **RestTemplate** | Bundled | Synchronous client for HTTP requests to external APIs |
| **RAWG API** | v1 | Live video game database provider |
| **Maven** | 3.x | Build automation and dependency management |

---

## 📡 API Reference

**Base URL:** `http://localhost:8080`

### 🔵 Health Check

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/home` | Verifies server operational status |

---

### 🟣 RAWG External API Integration (`/rawg`)

| Method | Endpoint | Query Parameters | Description |
|---|---|---|---|
| `GET` | `/rawg/games` | `search` (required), `genre` (optional), `page` (default: 1), `pageSize` (default: 10) | Search games live from RAWG API |
| `GET` | `/rawg/games/{id}` | - | Fetch detailed information for a specific RAWG game ID |

#### Example: GET `/rawg/games?search=witcher&pageSize=2`

**Sample Response:**
```json
{
  "totalGames": 48,
  "nextPage": "https://api.rawg.io/api/games?key=...&page=2&page_size=2&search=witcher",
  "previousPage": null,
  "games": [
    {
      "id": 3328,
      "name": "The Witcher 3: Wild Hunt",
      "released": "2015-05-18",
      "image": "https://media.rawg.io/media/games/618/618c47b7e69e34346083ee9174154fa9.jpg",
      "rating": 4.65
    },
    {
      "id": 1959,
      "name": "The Witcher 2: Assassins of Kings Enhanced Edition",
      "released": "2011-05-17",
      "image": "https://media.rawg.io/media/games/713/71372772097d206e70991c64549f0262.jpg",
      "rating": 4.17
    }
  ]
}
```

---

### 🟢 Local Game Management (`/games`)

| Method | Endpoint | Request Body | Description |
|---|---|---|---|
| `GET` | `/games` | - | Retrieve paginated list of local games |
| `GET` | `/games/{id}` | - | Get local game details by ID |
| `POST` | `/games` | `GameRequestDTO` | Add a new game to local database |
| `PUT` | `/games/{id}` | `GameRequestDTO` | Update existing game, genre, details, and platforms |

#### POST `/games`

**Sample Request Body:**
```json
{
  "name": "Cyberpunk 2077",
  "genre_id": 1,
  "gameDetails": {
    "developer": "CD Projekt Red",
    "releaseYear": 2020,
    "platform": "PC / Console"
  },
  "platform_ids": [1, 2]
}
```

**Sample Response (200 OK):**
```json
{
  "game_id": 1,
  "game_name": "Cyberpunk 2077",
  "genre_id": 1,
  "genre_name": "RPG",
  "section": "Home"
}
```

---

### ❤️ Favorites Management (`/game`)

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/game/favorites` | Retrieve list of favorite games |
| `POST` | `/game/addfavorites/{game_id}` | Add a game to favorites (changes section to `"Favorite"`) |
| `DELETE` | `/game/delete/{id}` | Remove a game from favorites (reverts section to `"Home"`) |

---

### 🏷️ Genre Management (`/genres`)

| Method | Endpoint | Request Body | Description |
|---|---|---|---|
| `GET` | `/genres` | - | Get all registered genres |
| `POST` | `/genres` | `{"name": "Action"}` | Create a new genre |

---

### 🎮 Platform Management (`/platforms`)

| Method | Endpoint | Request Body | Description |
|---|---|---|---|
| `GET` | `/platforms` | - | Get all registered gaming platforms |
| `POST` | `/platforms` | `{"name": "PlayStation 5"}` | Register a new gaming platform |

---

## 🗄 Data Model & Relational Mappings

```
  ┌──────────────┐         ┌──────────────┐
  │    genre     │ 1     * │     game     │
  ├──────────────┤─────────┼──────────────┤
  │ genre_id(PK) │         │ game_id (PK) │
  │ name         │         │ game_name    │
  └──────────────┘         │ section      │
                           │ genre_id(FK) │
  ┌──────────────┐         │ details_id(FK)
  │ GameDetails  │ 1     1 └──────┬───────┘
  ├──────────────┤────────────────┘ *
  │ details_id   │                  │ (game_platform)
  │ developer    │                  │ *
  │ releaseYear  │         ┌────────┴─────┐
  │ platform     │         │   Platform   │
  └──────────────┘         ├──────────────┤
                           │ platform_id  │
                           │ name         │
                           └──────────────┘
```

### Entities Summary
- **`game`**: Core entity representing a game title.
  - `@ManyToOne` → `genre` (`genre_id`)
  - `@OneToOne` → `GameDetails` (`details_id`)
  - `@ManyToMany` → `Platform` via join table `game_platform`
- **`genre`**: Categorization for games (`genre_id`, `name`).
- **`Platform`**: Gaming platforms such as PC, PS5, Xbox Series X (`platform_id`, `name`).
- **`GameDetails`**: Extended attributes including developer name, release year, and target specs.

---

## 🚀 Getting Started (Local Setup)

### Prerequisites

Ensure you have the following software installed:

- [ ] **Java 21 (JDK)** — [Download Adoptium OpenJDK 21](https://adoptium.net/)
- [ ] **Maven 3.8+** — [Download Apache Maven](https://maven.apache.org/)
- [ ] **PostgreSQL 14+** — [Download PostgreSQL](https://www.postgresql.org/)
- [ ] **Git** — [Download Git](https://git-scm.com/)

---

### Step 1 — Clone the Repository

```bash
git clone https://github.com/Harshvardhan210/Ludex.git
cd Ludex/ludex
```

### Step 2 — Create PostgreSQL Database

Open `psql` or pgAdmin and run:

```sql
CREATE DATABASE ludex;
```

### Step 3 — Configure `application.properties`

Update `src/main/resources/application.properties` with your PostgreSQL database credentials and RAWG API Key:

```properties
spring.application.name=ludex
spring.datasource.url=jdbc:postgresql://localhost:5432/ludex
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

rawg.api.key=YOUR_RAWG_API_KEY
rawg.api.url=https://api.rawg.io/api
```

> 💡 *Note: You can get a free RAWG API key at [https://rawg.io/apidocs](https://rawg.io/apidocs).*

### Step 4 — Build & Run the Application

```bash
# Clean and compile
mvnw.cmd clean package -DskipTests

# Run the Spring Boot App
mvnw.cmd spring-boot:run
```

---

## ☁️ Deployment Guide

### JAR Deployment

```bash
# Package the executable JAR
./mvnw clean package -DskipTests

# Run with custom environment variables
java -jar target/ludex-0.0.1-SNAPSHOT.jar \
  --spring.datasource.url=jdbc:postgresql://<DB_HOST>:5432/ludex \
  --spring.datasource.username=<DB_USER> \
  --spring.datasource.password=<DB_PASS> \
  --rawg.api.key=<RAWG_KEY>
```

---

## ⚠️ Current Condition & Status

| Category | Status | Details |
|---|---|---|
| **RAWG Integration** | ✅ Fully Functional | Live search & detailed lookup with DTO transformations |
| **DTO & Validation** | ✅ Fully Functional | Request/Response DTO separation with `@Valid` constraints |
| **Relational Data Model**| ✅ Fully Functional | JPA `@ManyToOne`, `@OneToOne`, and `@ManyToMany` mappings active |
| **Spring Security** | ✅ Configured | Permits HTTP endpoints with CSRF disabled for REST |
| **Pagination** | ✅ Fully Functional | `Pageable` integrated into `/games` GET endpoint |
| **Exception Handling** | ✅ Fully Functional | Global Handler returning structured JSON error payloads |

---

## 🗺 Future Roadmap

- [ ] **JWT Authentication:** Add JWT-based user login & registration.
- [ ] **Favorites Database Persistence:** Store user favorites in PostgreSQL tables.
- [ ] **Redis Caching:** Cache external RAWG API responses to optimize rate limits & latency.
- [ ] **Swagger/OpenAPI Documentation:** Integrate Springdoc OpenAPI UI at `/swagger-ui.html`.
- [ ] **Docker & Docker-Compose:** Containerize application and PostgreSQL database services.

---

## 👨‍💻 Author

**Harshvardhan** — [@Harshvardhan210](https://github.com/Harshvardhan210)

<div align="center">

**⭐ Star this repo if you find it helpful!**

</div>
