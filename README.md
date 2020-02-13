# spring-boot-rest-api
RESTful web service using Spring Boot.

### Setup Steps
**1. Clone the application**

```bash
https://github.com/LindoManana/spring-boot-rest-api.git
```

**2. Build and run the app using maven**

```bash
mvn spring-boot:run
```

The app will start running at <http://localhost:8080>.

###  Explore Rest APIs
The app defines the following CRUD APIs:

    GET /employees (Fetch all employees)
    
    POST /employees (Create an employee)
    
    GET /employees/{employeeId} (Fetch an employee by id)
    
    PUT /employees/{employeeId} (Update an employee)
    
    DELETE /employees/{employeeId} (Delete an employee)

###  Built With

* [Spring Boot](https://spring.io/projects/spring-boot) - Java-based framework
* [Maven](https://maven.apache.org/) - Dependency Management