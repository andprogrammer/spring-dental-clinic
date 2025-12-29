## Dental Clinic – Spring Boot API

This project is a simple Spring Boot REST API for managing patients in a dental clinic.
The application identifies patients who require a dental visit if their last visit was six months ago or earlier.
It uses Spring Web, Spring Data JPA, and an H2 in-memory database.

---

## System Requirements

- **Operating System**: Tested on Ubuntu 24.04.3 LTS (or newer).
- **Java Version**: JDK 21 (or later). You can install it using the instructions below.

## Repository
```bash
git clone git@github.com:andprogrammer/spring-dental-clinic.git
```

## Docker
```bash
docker build -t spring-dental-clinic .
```

```bash
docker run -d -p 8080:8080 --name spring-dental-clinic-container spring-dental-clinic
```

http://localhost:8080/patients

```bash
docker stop spring-dental-clinic-container
```

```bash
docker rm spring-dental-clinic-container
```