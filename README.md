# PlayerZero Task Manager

A simple REST API built with Java and Spring Boot for demonstrating software investigation, debugging, testing, and workflow automation with PlayerZero.

## Technologies

- Java 21
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 Database
- Maven
- JUnit 5
- Mockito

## Features

- Create tasks
- Retrieve all tasks
- Retrieve a task by ID
- Filter tasks by status
- Update tasks
- Delete tasks

## Task statuses

- `TODO`
- `IN_PROGRESS`
- `COMPLETED`

## Running the application

Navigate into the project:

```bash
cd playerzero-task-manager
```

Run the application:

```bash
mvn spring-boot:run
```

The application will start on:

http://localhost:8080

## API Endpoints

### Create a task

**POST** `/api/tasks`

Example request:

```json
{
  "title": "Implement login",
  "description": "Create login functionality",
  "status": "TODO"
}
```

### Get all tasks

**GET** `/api/tasks`

### Filter tasks by status

**GET** `/api/tasks?status=TODO`

Example:

```text
GET /api/tasks?status=COMPLETED
```

### Get a task

**GET** `/api/tasks/{id}`

### Update a task

**PUT** `/api/tasks/{id}`

Example:

```json
{
  "title": "Implement login",
  "description": "Complete login functionality",
  "status": "COMPLETED"
}
```

### Delete a task

**DELETE** `/api/tasks/{id}`

## Running tests

```bash
mvn test
```

## Purpose

This project was created as a sample application for demonstrating software investigation, debugging, testing, and workflow automation.