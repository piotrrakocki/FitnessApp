# FitnessApp

## Table of Contents

- [Description](#description)
- [Technologies](#technologies)
- [Features](#features)
- [How to Run](#how-to-run)
- [Author](#author)

## Description

**FitnessApp** is a backend for a fitness application built with Java and Spring Boot, designed to enable the creation and management of workout plans. The project leverages artificial intelligence (GPT API) to generate personalized workout plans based on user preferences and goals. FitnessApp ensures secure user authentication and integrates with MySQL for data storage.
## Technologies

The FitnessApp project is built using the following technologies:
- Java 21
- Spring Boot 3
- Spring Data JPA
- Spring Security
- Spring Boot Mail
- Spring Webflux
- JSON Web Tokens (JWT)
- Jackson Datatype JSR310
- MySQL
- Project Lombok
- DevTools
- Maven

## Features

- ##### User Authentication and Registration
  - Registration with Email Confirmation.
  - Login with JWT token.

- ##### Workout Plan Management
  - Create Workout Plans
  - Add Training Days
  - Add Exercises to Training Days
  - Edit Plans with Versioning

- ##### AI-Generated Workout Plans
  - Integration with GPT API allows for personalized workout plans based on user inputs.

- ##### Workout Sessions
  - Start Workout Session
  - Start Exercise Session
  - Add Exercises and Sets
  - End Workout Session

## How to Run
1. Make Sure You have Java Installed on your computer.
2. Clone this repository to your computer
3. Go to the project folder and run the application with the command:
```./mvnw spring-boot:run```
4. The Application will run on port 8080. 
5. You can now Open Your Browser and go to http://localhost:8080/home

## Author

The project was created by Piotr Rakocki.
