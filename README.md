Social Media App
Overview
This is a Social Media App built with Java Spring Boot, utilizing various technologies such as Spring Security, JWT, Apache Kafka, CORS, and Controller Advice. The application allows users to connect, share posts, and interact with each other in a social network setting.

Technologies Used
Java Spring Boot
Spring Security
JWT (JSON Web Tokens)
Apache Kafka
Controller Advice


Features
User Registration and Authentication
Create User , Post , Story , reels all the crud opration 
Post Creation and Sharing
User Connections and Interactions
Real-time Updates with Apache Kafka

Prerequisites
Java Development Kit (JDK) 17 
Apache Kafka installed and configured
Database (e.g., MySQL,) configured

Getting Started
Clone the Repository:

bash
Copy code
git clone https://github.com/yourusername/social-media-app.git
Configure Database:

Configure the application.properties file with your database details.

Run Apache Kafka:

Start the Apache Kafka server.

Build and Run the Application:

bash
Copy code
cd social-media-app
./mvnw clean install
./mvnw spring-boot:run
The application will be accessible at http://localhost:5454.
