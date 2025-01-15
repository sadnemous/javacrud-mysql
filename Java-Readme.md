GitHub Copilot
Sure, let's create a Spring Boot web application for CRUD operations on the employee table, implement Swagger for API documentation, and use JDBC, JUnit, and JaCoCo for testing and code coverage.

Step 1: Project Structure
```text
spring-boot-crud-app
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── crudapp
│   │   │               ├── CrudAppApplication.java
│   │   │               ├── controller
│   │   │               │   └── EmployeeController.java
│   │   │               ├── model
│   │   │               │   └── Employee.java
│   │   │               ├── repository
│   │   │               │   └── EmployeeRepository.java
│   │   │               └── service
│   │   │                   └── EmployeeService.java
│   │   └── resources
│   │       ├── application.properties
│   │       └── schema.sql
│   └── test
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── crudapp
│       │               ├── CrudAppApplicationTests.java
│       │               └── service
│       │                   └── EmployeeServiceTests.java
│       └── resources
│           └── application-test.properties
├── build.gradle.kts
└── README.md
```
Step 2: Create build.gradle.kts
```kotlin
plugins {
    id("org.springframework.boot") version "2.5.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.21"
    kotlin("plugin.spring") version "1.5.21"
    id("jacoco")
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
    implementation("org.springdoc:springdoc-openapi-ui:1.5.9")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.7.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.7.2")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

jacoco {
    toolVersion = "0.8.7"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
```
Step 3: Create CrudAppApplication.java
```java
/com/example/crudapp/CrudAppApplication.java
package com.example.crudapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrudAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrudAppApplication.class, args);
    }
}
```
Step 4: Create Employee.java
```java
package com.example.crudapp.model;

public class Employee {
    private int id;
    private String fname;
    private String lname;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
}
```
Step 5: Create EmployeeRepository.java
```java
package com.example.crudapp.repository;

import com.example.crudapp.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee", (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFname(rs.getString("fname"));
            employee.setLname(rs.getString("lname"));
            return employee;
        });
    }

    public Employee findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id = ?", new Object[]{id}, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFname(rs.getString("fname"));
            employee.setLname(rs.getString("lname"));
            return employee;
        });
    }

    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employee (id, fname, lname) VALUES (?, ?, ?)",
                employee.getId(), employee.getFname(), employee.getLname());
    }

    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE employee SET fname = ?, lname = ? WHERE id = ?",
                employee.getFname(), employee.getLname(), employee.getId());
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM employee WHERE id = ?", id);
    }
}
```

Step 6: Create EmployeeService.java
```java
package com.example.crudapp.service;

import com.example.crudapp.model.Employee;
import com.example.crudapp.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(int id) {
        return employeeRepository.findById(id);
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(Employee employee) {
        employeeRepository.update(employee);
    }

    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }
}
```
Step 7: Create EmployeeController.java
```java
package com.example.crudapp.controller;

import com.example.crudapp.model.Employee;
import com.example.crudapp.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return employeeService.getEmployeeById(id);
    }

    @PostMapping
    public void createEmployee(@RequestBody Employee employee) {
        employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        employee.setId(id);
        employeeService.updateEmployee(employee);
    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
    }
}
```

Step 8: Create application.properties
```java
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
springdoc.api-docs.path=/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
```
Step 9: Create schema.sql
```sql
CREATE TABLE IF NOT EXISTS employee (
    id INT PRIMARY KEY,
    fname VARCHAR(32),
    lname VARCHAR(32)
);
```
Step 10: Create CrudAppApplicationTests.java
```java
package com.example.crudapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrudAppApplicationTests {

    @Test
    void contextLoads() {
    }
}
```
Step 11: Create EmployeeServiceTests.java
```java
package com.example.crudapp.service;

import com.example.crudapp.model.Employee;
import com.example.crudapp.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class EmployeeServiceTests {

    @Autowired
    private EmployeeService employeeService;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    void testGetAllEmployees() {
        Employee employee1 = new Employee();
        employee1.setId(1);
        employee1.setFname("John");
        employee1.setLname("Doe");

        Employee employee2 = new Employee();
        employee2.setId(2);
        employee2.setFname("Jane");
        employee2.setLname("Smith");

        List<Employee> employees = Arrays.asList(employee1, employee2);

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();
        assertEquals(2, result.size());
        assertEquals("John", result.get(0).getFname());
        assertEquals("Jane", result.get(1).getFname());
    }
}
```
Step 12: Run the Application
To run the application, use the following command:
```bash
./gradlew bootRun
```
Step 13: Access Swagger UI
Once the application is running, you can access the Swagger UI at http://localhost:8080/swagger-ui.html.

Step 14: Run Tests and Generate JaCoCo Report
To run the tests and generate the JaCoCo report, use the following command:
```bash
./gradlew test jacocoTestReport
```
The JaCoCo report will be generated in build/reports/jacoco/test/html/index.html.
