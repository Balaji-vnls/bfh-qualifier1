# Bajaj Finserv Health | Qualifier 1 | JAVA

Spring Boot application for the Bajaj Finserv Health Qualifier 1 coding round.

## 🚀 Features
- On startup, sends a POST request to generate a webhook.
- Based on regNo, fetches SQL problem (Odd → Q1, Even → Q2).
- Submits the final SQL query with JWT token authorization.

## 🛠️ Requirements
- Java 11+ installed
- No need to install Maven (Maven Wrapper included)

## ▶️ How to Run
```bash
# Clean & package using Maven Wrapper
mvnw.cmd clean package

# Run the Spring Boot JAR
java -jar target\bfh-qualifier1-0.0.1-SNAPSHOT.jar
