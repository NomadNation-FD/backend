# NomadNation

NomadNation is a web application designed for digital nomads to discover offers, profiles, and reviews of various destinations worldwide. Users can register, log in, create profiles, search for offers, and leave reviews based on their experiences.

## Features

- **Authentication & Authorization:** Users can sign up and log in using email and password. Security is managed via JWT (JSON Web Tokens).
- **Profile Management:** Users can create and update their profiles, including uploading profile pictures.
- **Offers:** Users can search for and view available offers in different destinations.
- **Reviews:** Users can leave reviews for destinations, including comments and image uploads.
- **Email Notifications:** Users receive email notifications for important updates and offers.
- **API Documentation:** Automatically generated OpenAPI documentation.

## Project Structure

The project follows Domain-Driven Design (DDD) and Clean Architecture principles to ensure maintainability, scalability, and separation of concerns. The structure is organized into different layers, each responsible for specific functionality.

## Technologies Used

- **Spring Boot:** Main framework for application development.
- **Spring Security:** Authentication and authorization management.
- **Spring Data MongoDB:** Data access using MongoDB.
- **OpenAPI:** API documentation.
- **Cloudinary:** Image storage service.
- **SendGrid:** Email delivery service for sending notifications and updates.
- **Lombok:** Automatic code generation for boilerplate code.

## Setup

### Prerequisites

- **Java 21:** Ensure you have Java 21 installed.
- **Maven:** Ensure Maven is installed for dependency management.
- **MongoDB:** Make sure you have a running instance of MongoDB.