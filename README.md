## try a new CRUD RestAPI
### Step 1. https://start.spring.io/
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

### Step 2. Unzip and open with IntelliJ CE
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

#### 2. Tried to build but failed
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
#### 3. So far so good. I have added following 
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





