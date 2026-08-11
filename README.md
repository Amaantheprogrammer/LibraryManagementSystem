# Library Management System

A secure and scalable **Library Management System** built using **Spring Boot**, **Spring Security**, **JWT Authentication**, **Spring Data JPA**, and **MySQL**. The application provides secure user authentication and efficient book management through RESTful APIs.

## Features

### Authentication & Authorization

* User Registration
* User Login
* JWT-based Authentication
* Password Encryption using BCrypt
* Stateless Security with Spring Security

### Book Management

* Add New Books
* Update Book Details
* Search Books
* Retrieve Book Information
* Manage Library Catalog

### API Design

* RESTful API Architecture
* DTO-Based Request/Response Handling
* Global Exception Handling
* Request Validation
* Standardized API Responses

## Tech Stack

| Category   | Technology                 |
| ---------- | -------------------------- |
| Backend    | Spring Boot                |
| Security   | Spring Security, JWT       |
| Database   | MySQL                      |
| ORM        | Spring Data JPA, Hibernate |
| Mapping    | ModelMapper                |
| Validation | Jakarta Validation         |
| Build Tool | Maven                      |
| Language   | Java 17                    |
| Utilities  | Lombok                     |

## Project Structure

```text
src/main/java
│
├── auth
│   ├── controller
│   ├── dto
│   ├── jwt
│   ├── service
│   └── custom
│
├── book
│   ├── controller
│   ├── dto
│   ├── service
│   └── repository
│
├── config
├── exception
├── common_response
└── user
```

## Authentication Flow

```text
User Registration
        │
        ▼
Password Encrypted (BCrypt)
        │
        ▼
Stored in Database
        │
        ▼
User Login
        │
        ▼
Credentials Verified
        │
        ▼
JWT Generated
        │
        ▼
JWT Returned to Client
        │
        ▼
Protected APIs Accessed Using Token
```

## API Endpoints

### Authentication APIs

#### Register User

```http
POST /auth/register
```

#### Login User

```http
POST /auth/login
```

### Book APIs

#### Add Book

```http
POST /books
```

#### Get All Books

```http
GET /books
```

#### Get Book By ID

```http
GET /books/{bookId}
```

#### Update Book

```http
PATCH /books/update/{bookId}
```

## Database

The application uses **MySQL** for persistent data storage.

### Sample Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/librarydb
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## Getting Started

### Prerequisites

* Java 17+
* Maven
* MySQL

### Clone Repository

```bash
git clone https://github.com/your-username/LibraryManagementSystem.git
cd LibraryManagementSystem
```

### Configure Database

Update database credentials in:

```properties
src/main/resources/application.properties
```

### Run Application

Using Maven Wrapper:

```bash
./mvnw spring-boot:run
```

Or:

```bash
mvn spring-boot:run
```

Application will start on:

```text
http://localhost:8080
```

## Security Features

* JWT Authentication
* BCrypt Password Hashing
* Stateless Session Management
* Protected Endpoints
* Custom JWT Authentication Filter

## Exception Handling

The project includes centralized exception handling using:

* Custom Exceptions
* Global Exception Handler
* Consistent Error Responses


## Author

**Amaan Coatwala**

GitHub: https://github.com/Amaantheprogrammer
