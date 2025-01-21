## <mark>Trying a new CRUD RestAPI</mark>

> [!NOTE]
> it's a journy.. be patient.. It's kind of monologue. After two years will see how noob I was :laughing:

### <mark>Step 1. https://start.spring.io/</mark>
Project: Gradle - Kotlin
Language: Java
Spring Boot: 3.4.1
Project Metadata:
    Group: com.sadnemous
    Artifact: demoSpringBoot
    Name: demoSpringBoot
    Package Name: com.sadnemous.demoSpringBootSvc
    Packaging: Jar
    Java: 21
<br>
<img src="init.png">

### <mark>Step 2. Unzip and open with IntelliJ CE</mark>
#### 1. Gradle build failed<br>
##### <mark>Fix</mark>
Select proper JVM<br>
<img src="JVM-Selection.png">
<br>

Restart gradle build
```log
Starting Gradle Daemon...
Gradle Daemon started in 809 ms
> Task :prepareKotlinBuildScriptModel UP-TO-DATE
Download https://repo.maven.apache.org/maven2/com/google/protobuf/protobuf-java/4.26.1/protobuf-java-4.26.1.pom, took 21 ms
Download https://repo.maven.apache.org/maven2/com/google/protobuf/protobuf-parent/4.26.1/protobuf-parent-4.26.1.pom, took 18 ms
Download https://repo.maven.apache.org/maven2/com/google/protobuf/protobuf-bom/4.26.1/protobuf-bom-4.26.1.pom, took 17 ms
Download https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-starter-test/3.4.1/spring-boot-starter-test-3.4.1.module, took 36 ms
Download https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-test/3.4.1/spring-boot-test-3.4.1.module, took 19 ms
Download https://repo.maven.apache.org/maven2/com/jayway/jsonpath/json-path/2.9.0/json-path-2.9.0.module, took 16 ms
Download https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter/5.11.4/junit-jupiter-5.11.4.module, took 25 ms
Download https://repo.maven.apache.org/maven2/org/springframework/boot/spring-boot-test-autoconfigure/3.4.1/spring-boot-test-autoconfigure-3.4.1.module, took 19 ms
Download https://repo.maven.apache.org/maven2/org/springframework/spring-test/6.2.1/spring-test-6.2.1.module, took 19 ms
Download https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-api/5.11.4/junit-jupiter-api-5.11.4.module, took 23 ms
Download https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-engine/5.11.4/junit-jupiter-engine-5.11.4.module, took 25 ms
Download https://repo.maven.apache.org/maven2/org/junit/jupiter/junit-jupiter-params/5.11.4/junit-jupiter-params-5.11.4.module, took 36 ms
Download https://repo.maven.apache.org/maven2/org/opentest4j/opentest4j/1.3.0/opentest4j-1.3.0.module, took 18 ms
Download https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-commons/1.11.4/junit-platform-commons-1.11.4.module, took 18 ms
Download https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-engine/1.11.4/junit-platform-engine-1.11.4.module, took 21 ms
Download https://repo.maven.apache.org/maven2/org/apiguardian/apiguardian-api/1.1.2/apiguardian-api-1.1.2.module, took 19 ms
Download https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.11.4/junit-platform-launcher-1.11.4.pom, took 53 ms
Download https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.11.4/junit-platform-launcher-1.11.4.module, took 21 ms
Download https://repo.maven.apache.org/maven2/org/junit/platform/junit-platform-launcher/1.11.4/junit-platform-launcher-1.11.4.jar, took 97 ms

BUILD SUCCESSFUL in 9s
```

#### <mark>2. Tried to build but failed</mark>
```log

2025-01-18T15:15:48.315-06:00  WARN 49596 --- [demoSpringBootSvc] [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.BeanCreationException: Error creating bean with name 'dataSource' defined in class path resource [org/springframework/boot/autoconfigure/jdbc/DataSourceConfiguration$Hikari.class]: Failed to instantiate [com.zaxxer.hikari.HikariDataSource]: Factory method 'dataSource' threw exception with message: Failed to determine a suitable driver class
2025-01-18T15:15:48.316-06:00  INFO 49596 --- [demoSpringBootSvc] [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
```
<br>

##### <mark>Fix</mark>

updated application.properties file as follows <br>

```
spring.application.name=demoSpringBootSvc
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=soumen
spring.datasource.password=das
spring.datasource.driverClassName=com.mysql.jdbc.Driver
server.port=9008
```
####  <mark>3. So far so good. I have added following</mark>
model/Employee.java
```java
package com.sadnemous.demoSpringBootSvc.model;

public record Employee (
        int ID,
        String FirstName,
        String LastName
){}

```

controller/DemoSpringBootSvcController.java
```java
package com.sadnemous.demoSpringBootSvc.controller;

import com.sadnemous.demoSpringBootSvc.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()

public class DemoSpringBootSvcController {
    @GetMapping("/getempsvc")
    public ResponseEntity <Employee> getEmp ()
    {
        Employee emp = new Employee(31291, "Soumen", "Das");
        return ResponseEntity.ok(emp);

    }
}
```

curl.sh
```bash
curl -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -X GET http://localhost:9008/getEmpSvc 2> /dev/null|python3 -mjson.tool 
```
output
```json
{
    "timestamp": "2025-01-18T23:53:14.181+00:00",
    "status": 404,
    "error": "Not Found",
    "path": "/getEmpSvc"
}
```

<b><mark>Wow, endpoint is case sensitive!! Modified the curl.sh</mark></b><br>
```bash
curl -H "Accept: application/json" \
     -H "Content-Type: application/json" \
     -X GET http://localhost:9008/getEmpSvc 2> /dev/null|python3 -mjson.tool 
```

output:
```json
{
    "ID": 11089,
    "FirstName": "Soumen",
    "LastName": "Das"
}
```

####  <mark>4. Time to create service layer:</mark>
create dir service and create two java file:
```bash
mkdir service
touch service/IDemoSpringBootSvcService.java
touch service/DemoSpringBootSvcServiceImpl.java
```

Try 1:
```java
//service/IDemoSpringBootSvcService.java
package com.sadnemous.demoSpringBootSvc.service;

import com.sadnemous.demoSpringBootSvc.model.Employee;

public interface IDemoSpringBootSvcService {
    public Employee getEmp();
}
```
```java
//service/DemoSpringBootSvcServiceImpl.java
package com.sadnemous.demoSpringBootSvc.service;

import com.sadnemous.demoSpringBootSvc.model.Employee;

public class DemoSpringBootSvcServiceImpl implements IDemoSpringBootSvcService{
    @Override
    public Employee getEmp()
    {
        Employee emp = new Employee(21222, "Robin", "Hood");
        return emp;
    }
}
```
<mark>BEN STOKES!!! FAILED!! But Why?</mark><br>
```log
2025-01-18T18:17:50.633-06:00  WARN 56902 --- [demoSpringBootSvc] [           main] ConfigServletWebServerApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'demoSpringBootSvcController': Unsatisfied dependency expressed through field 'svcService': No qualifying bean of type 'com.sadnemous.demoSpringBootSvc.service.IDemoSpringBootSvcService' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {@org.springframework.beans.factory.annotation.Autowired(required=true)}
2025-01-18T18:17:50.633-06:00  INFO 56902 --- [demoSpringBootSvc] [           main] o.apache.catalina.core.StandardService   : Stopping service [Tomcat]
2025-01-18T18:17:50.639-06:00  INFO 56902 --- [demoSpringBootSvc] [           main] .s.b.a.l.ConditionEvaluationReportLogger : 
```
So the issue is `UnsatisfiedDependencyException` which means Spring Container does not have any idea about `IDemoSpringBootSvcService`<br>
To let Spring know about the class and make it as Bean we need `@Component` or `@Repository` or `@Service` etc.. annotation. <br>
Here I missed `@Service`..<br>
Let me add retry...<br>
<Mark> FIX</mark>
```java
//service/DemoSpringBootSvcServiceImpl.java
package com.sadnemous.demoSpringBootSvc.service;

import com.sadnemous.demoSpringBootSvc.model.Employee;
@Service
public class DemoSpringBootSvcServiceImpl implements IDemoSpringBootSvcService{
    @Override
    public Employee getEmp()
    {
        Employee emp = new Employee(21222, "Robin", "Hood");
        return emp;
    }
}
```
Try 2. Now run.. 
<b>BUILD SUCCESS!!! OOOOO Maa Go Turu Love!! </b><br>
Let me run the curl.sh<br>
OUTPUT<br>
```bash
$ ./curl.sh
{
    "ID": 21222,
    "FirstName": "Robin",
    "LastName": "Hood"
}

```
####  <mark>5. So far so good, so Response is getting returned from Service Layer.. Now let me try to implement Repository:</mark>
create dir `repository` and create one java file, this time use IntelliJ IDE..







####  <mark>Comparison knowledge DTO, Entity and Record</mark>
|Sl|DTO|Record|Entity|
|---|---|---|---|
|1|Data Transfer Object|Same|business domain
|2|transferring data from Controller -> Service Layer -> Repository|same|Represents a persistent data structure mapped to a database table using an ORM like JPA
|3|Simple POJO Class|record keyword|@Entity annotation required|
|4|Mutable|Immutable|Mutable|
|5|<pre>public class UserDTO {<br>   private String userid;<br>   private int age;<br>   // Getters and setters<br> }<br></pre>|``` record User(String userid, int age) { }```|<pre> @Entity<br> @Table(name = "users")<br> public class User<br>{<br>   @Id<br>   @GeneratedValue<br>   private Long id;<br>   private String userid;<br>   private int age;<br>   private String address;<br>   private String alien_status;<br>   // Getters and setters<br> }<br></pre>|
|6|DTO to Domain mapper to be implemented, to be used in Repo|RowMapper to be implemented|Domain to DTO mapper needed

#### <mark>What is Record?</mark>
In Java, a record is a special kind of class introduced in Java 14 that acts as a transparent carrier for immutable data. It's a concise way to define a class that primarily holds data, without the need to write boilerplate code like constructors, getters, setters, equals(), hashCode(), and toString() methods

[!IMPORTANT]  
<b>Key Points:</b>
- <b>Immutable Data</b>:
  The fields of a record are final, meaning they cannot be changed once the record is created.
- <b>Concise Syntax</b>:
  The declaration is simpler than a traditional class.
- <b>Automatic Generation</b>:
The compiler automatically generates the constructor, getters, equals(), hashCode(), and toString() methods.
- <b>Nominal Tuple</b>:
Records can be thought of as nominal tuples, providing a named structure for holding data.  <br><br>

<b>Let us see with an Example:</b>
```java
//Person.java
record Person(String name, int age) {}
```

Compile the record.
```bash
javac Person.java
```

Use javap to disassemble.
```bash
javap Person.class
```

Output:
```java
Compiled from "Person.java"
public record Person(java.lang.String name, int age) {
  public Person(java.lang.String, int);
  public java.lang.String name();
  public int age();
  public final boolean equals(java.lang.Object);
  public final int hashCode();
  public final java.lang.String toString();
}
```

Explanation:
- javap shows that the Person record is a public class.
- It automatically generates the constructor, accessor methods (name() and age()), equals(), hashCode(), and toString() methods.
- The record implicitly extends the java.lang.Record class.


Key Points:
- Records are designed to be simple, immutable data carriers.
- They automatically generate common methods, reducing boilerplate code.
- javap helps you understand the underlying structure and bytecode of records.


<mark>Common Use Cases</mark>:
- Data Transfer Objects (DTOs): Records are ideal for representing data that needs to be transferred between different parts of an application.
- API Responses: Records can be used to structure the data returned from API calls.
- Immutable Data Structures: Records provide a convenient way to create immutable data structures, which can help make your code more reliable and easier to reason about.



#### <mark>What is RowMapper?</mark>
A `RowMapper` in Spring JDBC is a functional interface used to map a single row of a result set (from a database query) to a corresponding object in your Java application. It’s typically used with the JDBCTemplate class for query operations, allowing you to transform each row of the result set into a domain object.


[!IMPORTANT]  
Key Points About `RowMapper`:
 1. It simplifies the mapping of database rows to Java objects, reducing boilerplate code.
 2. It's particularly useful when you need custom mapping logic or when working with complex result sets.
 3. It is used with methods like `query()` in `JdbcTemplate`.
<br><br>

 `RowMapper` Functional Interface:
 

 ```java
 @FunctionalInterface
public interface RowMapper<T> {
    T mapRow(ResultSet rs, int rowNum) throws SQLException;
}
```
  `mapRow(ResultSet rs, int rowNum):` This method maps a single row of data from the `ResultSet` to an object of type `T`.


Example: Using `RowMapper` in `JDBCTemplate`
```java
// If you have a POJO class called "Customer" with properties like id, name, etc.
//com.example.custormer.model
//CustomerRowMapper.java
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        // ... set other properties
        return customer;
    }
}

// In your DAO class
//com.example.custormer.dao or 
//com.example.custormer.repository or 
//CustomerRepository.java
JdbcTemplate jdbcTemplate;

public List<Customer> getAllCustomers() {
    String sql = "SELECT * FROM customers";
    return jdbcTemplate.query(sql, new CustomerRowMapper()); 
}
```

```java
// If you have a Record "Customer" with properties like id, name, etc.
//com.example.custormer.model
//CustomerRowMapper.java
public class CustomerRowMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer(
          rs.getInt("id"),
          rs.getString("name")
        );
        return customer;
    }
}

// In your DAO or Repository class
//com.example.custormer.dao or 
//com.example.custormer.repository or 
//CustomerRepository.java
JdbcTemplate jdbcTemplate;

public List<Customer> getAllCustomers() {
    String sql = "SELECT * FROM customers";
    return jdbcTemplate.query(sql, new CustomerRowMapper()); 
}
```

One thing if we want to avoid separate RowMapper class we could use lambda function
```java
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

public class Example {

    private final JdbcTemplate jdbcTemplate;

    public Example(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers() {
        String sql = "SELECT id, name, email FROM users";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email")
        ));
    }
}

record User(int id, String name, String email) {}
```


#### <mark>What should I use service to repository dto or entity or record?</mark>
When using a service layer, you should generally interact with the repository using `Data Transfer Objects (DTOs)`, <br>rather than directly using `entities` or `records`,<br> as `DTO`s provide a cleaner separation of concerns by acting as a dedicated <b>data transfer mechanism</b> between layers <br>and allowing for controlled data exposure to the presentation layer, <br>while entities should remain within the data access layer

#### <mark>Where should I keep my DTO class?</mark>
In a Spring Boot application, you should typically place your DTO (Data Transfer Object) classes in a separate package named "dto", which is usually considered a part of the "controller" layer, as DTOs are primarily used for data exchange between the client and the application's API endpoints managed by controllers.<br>
<br><b>Explanation:</b><br>
<br><b>Model:</b><br>
The "model" package is usually reserved for entities that directly represent data in your database, which are not exactly the same as DTOs as they may contain additional fields not needed for client communication.<br>
<br><b>View:</b><br>
The "view" package is for presentation logic, like handling how data is displayed to the user, and is not directly related to DTOs.<br>
<br><b>Controller:</b><br>
Since DTOs are used to transfer data between your application and the outside world (clients), the "controller" package is the most logical place to put them.<br>

<br><b>Repository:</b><br>
The "repository" package is for interacting with the database, and DTOs are not meant to be used in this layer.

<br><b>
Key points about DTOs:
</b><br>
 - <b>Data encapsulation</b>: DTOs encapsulate the data needed for a specific API request or response, often including only a subset of fields from the corresponding entity model.
 - <b>Decoupling</b>: By using DTOs, you can decouple the presentation layer (controller) from the data layer (repository and entities).
Validation: DTOs are often used to implement validation logic for incoming data.<br>
<br><b>
Example project structure:
</b><br>
```
src
  - main
    - java
      - com.example
        - Application.java

        - controller
          - UserController.java

        - dto
          - UserDto.java

        - model
          - User.java

        - repository
          - UserRepository.java 
```


####  <mark>6. What is the status of trying getting data from dababase?</mark>
Oh!! I forgot, yeah it was a ride, 
- so many new concepts,
- confusion like DTO or Records or POJO class,
- confusion like repository or dao..
- rowmapper
- canonical constructor - simple thing heavy name 😆
- My Record class has field name ID, because it starts with Uppercase there was issue.. and error msg was not at all helping. may be because I am really new to this world.
- Now all good and uploaded to https://github.com/sadnemous/javacrud-mysql/tree/main/demoSpringBootSvc


#### <mark>7. What Next?</mark>
Many things are unknown, will cover and implement like
- Unit testing (JUnit)
- Test Coverage (Jacoco)
- Full Fledge Exception Handling..
- swagger







#### Important Read:<br>
|Desc|Link|
|---|---|
|JdbcTemplate Querying Examples|https://mkyong.com/spring/spring-jdbctemplate-querying-examples/|










