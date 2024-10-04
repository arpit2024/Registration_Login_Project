# User Registration and Login System

This is a Spring Boot application that handles user registration and login with authentication using **Spring Security**. It uses **Bcrypt** to encrypt passwords before storing them in the database.

## Features
- User registration with email and password.
- Email-based login authentication.
- Password encryption using **Bcrypt**.
- Validation for form inputs (e.g., email format, password constraints).
- Displays a list of all registered users (accessible to admin).
- Integration with a relational database to persist user details.

## Technologies Used
- **Spring Boot**: Framework for building Java-based web applications.
- **Spring Security**: Handles authentication and authorization.
- **Thymeleaf**: Template engine for server-side rendering of web pages.
- **Hibernate/JPA**: For persistence and interacting with the database.
- **Bcrypt**: Secure password hashing.
- **Jakarta Bean Validation**: For validating user input on the registration form.

**Prerequisites**
- Maven
- MySQL or any other supported relational database
- IDE (e.g., IntelliJ IDEA, Eclipse)

The application will start on http://localhost:8080.

**Endpoints**

- **URL: /index**
Description: Displays the home page.
Registration

- **URL: /register**
Description: Displays the registration form.
Login

- **URL: /login**
Description: Displays the login page for user authentication.

- **URL: /logout**
Description: Displays the logout page for user authentication.


**User Registration Flow**
- Navigate to /register.
- Fill out the registration form with a unique email and a password.
- Submit the form. If validation passes, the user is saved in the database with an encrypted password.
- After successful registration, the user will be redirected to the same form with a success message.