# spring-boot-rest-api
CRUD (Create, Read, Update, and Delete) RESTful API using Spring Boot.

### Setup Steps
**1. Clone the application**

```bash
git clone https://github.com/LindoManana/spring-boot-rest-api.git
```

**2. Build and run the app using maven**

```bash
mvn clean install
mvn spring-boot:run
```

The applicaton will start running at <http://localhost:8080>.

###  Explore Rest APIs
The applicaton defines the following CRUD APIs:

    POST /employees (Create an employee)
    
    GET /employees/{employeeId} (Fetch an employee by id)
    
    GET /employees (Fetch all employees)
    
    PUT /employees/{employeeId} (Update an employee)
    
    DELETE /employees/{employeeId} (Delete an employee)

###  Built With / References

* [Spring Boot](https://spring.io/projects/spring-boot) - Java-based framework
* [Maven](https://maven.apache.org/) - Dependency management
* [MapStruct](https://mapstruct.org/) - Java Bean mapper