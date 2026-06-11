# Ticketing App

A full-stack support ticketing application. Users can be registered and authenticated, and tickets can be created, viewed, updated, and deleted. The backend is a Spring Boot REST API backed by MySQL; the frontend is a lightweight static site built with HTML, CSS, and vanilla JavaScript.

## Features

- **User management** — Create users with roles (`ADMIN`, `USER`), list users, and fetch a user's tickets
- **Ticket management** — Full CRUD for support tickets with priority (`LOW`, `MEDIUM`, `HIGH`) and status (`OPEN`, `IN_PROGRESS`, `RESOLVED`, `CLOSED`)
- **Authentication** — JWT-based login with BCrypt password hashing
- **Frontend** — Ticket list and detail pages that consume the REST API

## Tech Stack

| Layer    | Technology                                      |
| -------- | ----------------------------------------------- |
| Backend  | Java 21, Spring Boot 4, Spring Data JPA, Spring Security |
| Database | MySQL 8                                         |
| Auth     | JWT (jjwt), BCrypt                              |
| Frontend | HTML, CSS, JavaScript (no framework)            |
| Build    | Maven                                           |

## Project Structure

```
ticketing_app/
├── backend/                  # Spring Boot API
│   ├── src/main/java/        # Controllers, services, entities, security
│   ├── src/main/resources/   # application.yaml
│   ├── src/test/             # Unit tests
│   └── docker-compose.yml    # Local MySQL instance
└── frontend/                 # Static web UI
    ├── index.html            # Ticket list page
    ├── pages/                # Ticket detail page
    ├── api.js                # Shared API helpers
    └── style.css
```

## Prerequisites

- **Java 21**
- **Maven** (or use the included `./mvnw` wrapper)
- **Docker** (for the local MySQL database)
- A web browser and optional static file server for the frontend

## Getting Started

### 1. Start the database

From the `backend/` directory, start MySQL with Docker Compose:

```bash
cd backend
docker compose up -d
```

This starts MySQL on port **3307** with:

- Database: `ticketing_app_db`
- Root password: `mysql`

### 2. Configure environment variables

Create a `backend/.env` file (this file is gitignored):

```env
DATABASE_URL=jdbc:mysql://localhost:3307/ticketing_app_db
DATABASE_USERNAME=root
DATABASE_PASSWORD=mysql
```

Export these variables in your shell before starting the app, or configure them in your IDE run configuration.

### 3. Run the backend

```bash
cd backend
./mvnw spring-boot:run
```

The API starts at `http://localhost:8080`. Hibernate is configured with `ddl-auto: update`, so tables are created automatically on first run.

### 4. Run the frontend

Open `frontend/index.html` in a browser, or serve the `frontend/` directory with any static file server:

```bash
cd frontend
python3 -m http.server 5500
```

Then visit `http://localhost:5500`.

CORS is configured to allow requests from `localhost`, `127.0.0.1`, and `file://` origins.

## API Reference

Base URL: `http://localhost:8080/api/v1`

### Authentication

| Method | Endpoint   | Description              |
| ------ | ---------- | ------------------------ |
| POST   | `/login`   | Authenticate and receive a JWT |

**Login request body:**

```json
{
  "username": "jane",
  "password": "secret"
}
```

**Login response:**

```json
{
  "token": "<jwt>",
  "userId": 1,
  "username": "jane",
  "userRole": "USER"
}
```

Protected endpoints require an `Authorization: Bearer <token>` header.

### Users

| Method | Endpoint            | Description                |
| ------ | ------------------- | -------------------------- |
| POST   | `/user`             | Create a new user          |
| GET    | `/users`            | List all users             |
| GET    | `/user/{id}`        | Get a user by ID           |
| GET    | `/user/{id}/tickets`| Get tickets owned by a user|

**Create user request body:**

```json
{
  "username": "jane",
  "email": "jane@example.com",
  "password": "secret",
  "userRole": "USER"
}
```

### Tickets

| Method | Endpoint              | Description          |
| ------ | --------------------- | -------------------- |
| POST   | `/ticket`             | Create a ticket      |
| GET    | `/tickets`            | List all tickets     |
| GET    | `/tickets/{ticketId}` | Get a ticket by ID   |
| PUT    | `/tickets/{ticketId}` | Update a ticket      |
| DELETE | `/tickets/{ticketId}` | Delete a ticket      |

**Create ticket request body:**

```json
{
  "ticketTitle": "Cannot log in",
  "ticketDescription": "Getting a 500 error on the login page.",
  "ticketOwnerUsername": "jane",
  "ticketPriority": "HIGH",
  "ticketStatus": "OPEN"
}
```

Ticket IDs follow the format `ticket-1`, `ticket-2`, etc.

## Running Tests

```bash
cd backend
./mvnw test
```

Tests cover `TicketService` and `UserService` logic.

## Data Model

**User**

- `id`, `username`, `email`, `password` (hashed), `userRole`, `isActive`, `createdAt`, `updatedAt`

**Ticket**

- `ticketId`, `ticketTitle`, `ticketDescription`, `ticketOwner` (User), `ticketPriority`, `ticketStatus`, `createdAt`, `updatedAt`

## Error Handling

The API returns consistent HTTP status codes via a global exception handler:

| Status | Condition                    |
| ------ | ---------------------------- |
| 404    | User or ticket not found     |
| 401    | Invalid login credentials    |
| 403    | Inactive user account        |
