
## Bookstore API

### Project Overview

This is a RESTful backend application built using Java and Spring Boot. It manages books and authors and demonstrates industry-standard API practices such as CRUD operations, pagination, filtering, sorting, and auto-generated documentation using Swagger. All endpoints are tested and bundled into a Postman collection.

---

### Technologies Used

- Java  
- Spring Boot (JPA, Web)  
- H2 In-Memory Database  
- Postman  
- Swagger (OpenAPI Documentation)

---

### Features

- Full CRUD operations for both `Book` and `Author` entities  
- Pagination and sorting on `GET /books` endpoint  
- Author linkage via `authorId` during book creation  
- Swagger UI for interactive documentation  
- Postman collection covering all major endpoints  
- Clean code, structured project layout, and README instructions

---

### Setup Instructions

1. Clone the repository:

```bash
git clone https://github.com/Madhu6125/bookstore-api.git
cd bookstore-api
```

2. Run the Spring Boot application using your IDE or:

```bash
./mvnw spring-boot:run
```

3. Access the H2 Console:  
   URL: `http://localhost:8080/h2-console`  
   Default JDBC URL: `jdbc:h2:mem:testdb`

4. Access Swagger UI:  
   URL: `http://localhost:8080/swagger-ui/index.html`

---

### API Documentation

All endpoints are exposed and documented under Swagger, including:

- `GET /books?page=0&size=5&sort=title`
- `POST /books` with `authorId`
- `GET /authors`, `POST /authors`, and full CRUD for both models

---

### Postman Collection

Included as `Bookstore API.postman_collection.json` with sample data:
- Add/Get/Update/Delete for both Books and Authors
- Pagination and filtering via query parameters
- Exported and validated with working JSON payloads

---

### Deliverables

- Full source code
- Postman collection file
- Swagger documentation link
- Final 2-page project report (PDF)
