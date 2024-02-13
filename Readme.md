# Lord of the Rings Heroes API

This Spring Boot application provides a REST API to manage heroes from the Lord of the Rings universe, allowing operations such as creating heroes, listing all heroes, and simulating fights between them.

## Requirements

- Java 17
- Maven

## Running the Project

1. **Clone the Repository**: `git clone [https://github.com/paulwolfe0313/LordofTheRingsAPI.git]`
2. **Navigate to the Project Directory**: `cd [project directory]`
3. **Build the Project**: `mvn clean install`
4. **Run the Application**: `mvn spring-boot:run` or Navigate to AppStarter.java and Run

## Populating Data

To populate the database with sample heroes, send a POST request to `/heroes/populate` using a tool like Postman. No request body is required for this operation.

## Project Status

This project is a submission for [Midterm Practicum-v2 Paul Wolfe]. It implements all required functionalities, including CRUD operations for hero entities and simulating fights based on hero strength. Further enhancements could include more detailed fight simulations and additional hero attributes.
