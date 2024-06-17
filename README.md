
# Java Spring Boot REST API

This project is a REST API experiment built with Java Spring Boot 3.3.0. The purpose of this project is to learn and demonstrate how to create and secure a REST API.

## Table of Contents

- [Features](#features)
- [Getting Started](#getting-started)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)

## Features

- Basic REST API with a a `Hello` entity model and a `HelloController` to return a series of messages from the database.
- Secured API endpoints using Spring Security with HTTP Basic Authentication.

## Getting Started

These instructions will help you set up the project on your local machine for development and testing purposes.

### Prerequisites

- Java 11 or higher
- Maven
- Git

### Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/justinsovine/spring-boot-api-test.git
    ```
2. Navigate to the project directory:
    ```bash
    cd spring-boot-api-test
    ```
3. Build the project:
    ```bash
    mvn clean package
    ```

## Configuration

### Security

The security configuration is defined in the `WebSecurityConfiguration` class located in the `config` package. Authentication credentials are configured in the `application.properties` file.

## Running the Application

1. To run the application, use the following command:
    ```bash
    java -jar ./target/api-test.jar
    ```
2. The application will start and you can access the API at `http://localhost:55555`.
