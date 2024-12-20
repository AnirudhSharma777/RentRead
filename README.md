<!-- # RentRead
RentRead/
├── src/main/java/com/example/rentread
│   ├── controller
│   │   ├── AuthController.java
│   │   ├── BookController.java
│   │   └── RentalController.java
│   ├── dto
│   │   ├── LoginRequest.java
│   │   └── RegisterRequest.java
│   ├── entity
│   │   ├── Book.java
│   │   ├── Rental.java
│   │   └── User.java
│   ├── repository
│   │   ├── BookRepository.java
│   │   ├── RentalRepository.java
│   │   └── UserRepository.java
│   ├── service
│   │   ├── AuthService.java
│   │   ├── BookService.java
│   │   └── RentalService.java
│   ├── security
│   │   └── SecurityConfig.java
│   ├── exception
│   │   ├── ResourceNotFoundException.java
│   │   └── UnauthorizedException.java
│   ├── RentReadApplication.java
├── src/main/resources
│   ├── application.properties
├── src/test/java/com/example/rentread
│   ├── AuthControllerTest.java
│   ├── BookControllerTest.java
│   └── RentalControllerTest.java -->

# RentRead - Online Book Rental System

RentRead is an online book rental system built using **Spring Boot** and **MySQL**. This API allows users to register, log in, browse books, rent and return books, and perform administrative actions such as creating, updating, and deleting books.

## Table of Contents
1. [Installation](#installation)
2. [Running the Application](#running-the-application)
3. [API Documentation](#api-documentation)
4. [Postman Collection](#postman-collection)
5. [Technologies Used](#technologies-used)
6. [License](#license)

---

## Installation

### Prerequisites

- **Java 17** (or higher)
- **Maven** (or **Gradle**)
- **MySQL** (or any relational database)
- **Postman** (optional, for testing API endpoints)

### Clone the Repository

```bash
git clone https://github.com/AnirudhSharma777/RentRead.git
cd rentread
```

### Configure MySQL

1. Create a new MySQL database:

   ```sql
   CREATE DATABASE rentread;
   ```

2. Update the **`application.properties`** (or **`application.yml`**) in the `src/main/resources` folder with your MySQL database credentials.

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/rentread
spring.datasource.username=root
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

---

## Running the Application

### Using Gradle
To build and run the application, use the following commands:

1. **Build the project**:

```bash
gradle build
```

2. **Run the application**:

```bash
gradle bootRun
```

The application will start on `http://localhost:8080`.
---

## API Documentation

### Authentication Endpoints

#### **POST /api/v1/auth/register** - User Registration

- **Request Body**:
```json
{
    "email": "user@example.com",
    "password": "password123",
    "firstName": "John",
    "lastName": "Doe"
}
```

#### **POST /api/v1/auth/login** - User Login

- **Request Body**:
```json
{
    "email": "user@example.com",
    "password": "password123"
}
```

**Response**:
```json
{
    "token": "jwt_token_here"
}
```

---

### Book Management Endpoints

#### **GET /api/v1/books** - Get All Books

- **Headers**:
  - `Authorization: Bearer <your_jwt_token>`

#### **POST /api/v1/books** - Create a New Book (Admin Only)

- **Request Body**:
```json
{
    "title": "The Great Gatsby",
    "author": "F. Scott Fitzgerald",
    "genre": "Fiction",
    "availabilityStatus": "AVAILABLE"
}
```

#### **PUT /api/v1/books/{id}** - Update a Book (Admin Only)

- **Request Body**:
```json
{
    "title": "The Great Gatsby - Updated",
    "author": "F. Scott Fitzgerald",
    "genre": "Classic Fiction",
    "availabilityStatus": "AVAILABLE"
}
```

#### **DELETE /api/v1/books/{id}** - Delete a Book (Admin Only)

---

### Rental Management Endpoints

#### **POST /api/v1/rentals/rent/{userId}/{bookId}** - Rent a Book

- **Headers**:
  - `Authorization: Bearer <your_jwt_token>`

**Response**:
```json
{
    "id": 1,
    "user": {
        "id": 1,
        "name": "John Doe",
        "email": "user@example.com"
    },
    "book": {
        "id": 1,
        "title": "The Great Gatsby",
        "author": "F. Scott Fitzgerald",
        "availabilityStatus": "UNAVAILABLE"
    },
    "rentalDate": "2024-11-28"
}
```

#### **POST /api/v1/rentals/return/{rentalId}** - Return a Rented Book

- **Headers**:
  - `Authorization: Bearer <your_jwt_token>`

**Response**:
```json
{
    "id": 1,
    "user": {
        "id": 1,
        "name": "John Doe",
        "email": "user@example.com"
    },
    "book": {
        "id": 1,
        "title": "The Great Gatsby",
        "author": "F. Scott Fitzgerald",
        "availabilityStatus": "AVAILABLE"
    },
    "rentalDate": "2024-11-28",
    "returnedDate": "2024-11-29"
}
```


## Technologies Used

- **Spring Boot** - Framework for building the API.
- **MySQL** - Database for storing user and book data.
- **JWT** - For secure authentication and authorization.
- **Postman** - For API testing.
- **Lombok** - To reduce boilerplate code.

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
