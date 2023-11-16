# spring-boot-REST-CRUD-operations

This project demonstrate the design and implement a Spring Boot REST API with CRUD operations on Employee Entity.
This application user Spring Boot in-memory Database (H2) to save Employee Entity. H2 database can be accessed through the UR given below(Credentials provided).

Application url -  http://localhost:8080/api/v1/employees/
Swagger UI : http://localhost:8080/swagger-ui/index.html
H2 DB link : http://localhost:8080/h2-ui     username : sa  pwd:123

H2 DB connection info:
Driver class : org.h2.Driver
Connection url: jdbc:h2:mem:empmgmtdbjdbc:h2:mem:testdb
user name: sa
password: 123

Sample api calls
 
Create:
 method: Post
 url: localhost:8080/api/v1/employees/
 Request body:
 
 {
    "firstName": "first",
    "lastName": "last",
    "emailAddress": "test@test.com",
    "phone": 343343433,
    "birthDate": "2020-10-10"
}

Update:
 method: Put
 url: localhost:8080/api/v1/employees/1
 Request body:
 
 {
    "firstName": "first",
    "lastName": "last",
    "emailAddress": "test@test.com",
    "phone": 343343433,
    "birthDate": "2020-10-10"
}

Delete:
method: Delete
 url: localhost:8080/api/v1/employees/1
 
Get Employee:
method: Get
 url: localhost:8080/api/v1/employees/1
 
Get All Employees:
method: Get
 url: localhost:8080/api/v1/employees
