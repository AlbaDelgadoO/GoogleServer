# StravaAPI - Sports Tracking Application

This project is a RESTful API for a sports tracking application similar to Strava. Users can register, log in, create training sessions, accept challenges, and track progress on challenges.

## Table of Contents

- [Features](#features)
- [Technologies Used](#technologies-used)
- [Prerequisites](#prerequisites)
- [Usage](#usage)
  - [Authentication Endpoints](#authentication-endpoints)
  - [Training Session Endpoints](#training-session-endpoints)
  - [Challenge Endpoints](#challenge-endpoints)
- [Project Tree](#project-tree)

## Features

- **User Authentication**: Users can register and log in to access various features.
- **Training Sessions**: Users can create and manage training sessions, specifying details like title, sport, distance, and duration.
- **Challenges**: Users can create, accept, and track challenges to stay motivated.

## Technologies Used

- **Java 17**: Programming language.
- **Spring Boot**: Framework for building the API.
- **Spring MVC**: Web request handling.
- **Spring Security**: Authentication and authorization.
- **Maven**: Dependency management and build automation.
- **Swagger**: API documentation.
- **SLF4J & Logback**: Logging framework.

## Prerequisites

- **Java Development Kit (JDK) 17 or higher**
- **Maven**: To build and manage dependencies.
- **IDE**: IntelliJ IDEA, Eclipse, or a similar tool.
- **Postman**: For testing API endpoints.

## Usage

### Authentication Endpoints

- **Register User**  
  **URL**: `POST /auth/register`  
  **Headers**: `Content-Type: application/json`  
  **Body**:
  ```json
  {
    "name": "John Doe",
    "email": "john.doe@example.com",
    "password": "password123",
    "birthdate": "1990-01-01",
    "weight": "75kg",
    "height": "1.80m",
    "maxHR": 190,
    "restHR": 60
  }

- **Login User**  
  **URL**: `POST /auth/login`  
  **Headers**: `Content-Type: application/json`  
  **Body**:
  ```json
  {
  "email": "john.doe@example.com",
  "password": "password123"
  }

### Training Session Endpoints

- **Create Training Session**
**URL**: `POST /strava/trainingSession`  
  **Headers**: `Content-Type: application/json`
  **Authorization**: `your_auth_token`
  **Body**:
  ```json
  {
  "title": "Morning Run",
  "sport": "Running",
  "distance": 5.0,
  "startDate": "2024-11-08T07:00:00",
  "startTime": 7.0,
  "duration": 30.0
  }

- **Get Training Sessions**
  **URL**: `GET /strava/trainingSessionsQuery`  
  **Authorization:** `your_auth_token`
  **Optional Query Parameters:**
    `startDate: YYYY-MM-DD (e.g., 2024-11-01)`
    `endDate: YYYY-MM-DD (e.g., 2024-11-08)`
  
### Challenge Endpoints

- **Create Challenge**  
  **URL**: `POST /strava/challenge`  
  **Headers**:  
    - `Authorization: `your_auth_token`
  **Body**:
   ```json
     {
       "name": "Marathon Prep",
       "startDate": "2024-02-01T00:00:00",
       "endDate": "2024-03-01T23:59:59",
       "targetDistance": 42.195,
       "targetTime": 180,
       "sport": "Running"
     }

- **View Active Challenges**  
  **URL**: `GET /strava/activeChallenges`  
  **Headers**:  
    - `Authorization: `your_auth_token`

- **Accept a Challenge**  
  **URL**: `POST /strava/acceptChallenge`  
  **Headers**:  
    - `Authorization: `your_auth_token`  
  **Parameters**:  
    - `challengeName`: Name of the challenge to accept

- **Check Challenge Status**  
  **URL**: `GET /strava/challengeStatus`  
  **Headers**:  
    - `Authorization: `your_auth_token`  
  **Parameters**:  
    - `challengeName`: Name of the challenge to check

## Project Tree
```bash
Strava [boot]
├── src
│   ├── main
│   │   ├── java
│   │   │   └── es.deusto.sd.strava
│   │   │       ├── DataInit.java
│   │   │       ├── StravaApplication.java
│   │   │       ├── dto
│   │   │       │   ├── ChallengeDTO.java
│   │   │       │   ├── LoginDTO.java
│   │   │       │   ├── TrainingSessionDTO.java
│   │   │       │   └── UserDTO.java
│   │   │       ├── entity
│   │   │       │   ├── Challenge.java
│   │   │       │   ├── TrainingSession.java
│   │   │       │   └── User.java
│   │   │       ├── facade
│   │   │       │   ├── AuthController.java
│   │   │       │   └── StravaController.java
│   │   │       └── service
│   │   │           ├── AuthService.java
│   │   │           ├── ChallengeService.java
│   │   │           └── TrainingSessionService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── banner.txt
│
├── build.gradle
├── gradle
│   └── (archivos y carpetas de Gradle)
├── gradlew
├── gradlew.bat
├── HELP.md
├── README.md
└── settings.gradle
```
