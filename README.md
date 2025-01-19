## trying a new CRUD RestAPI, it's a journy.. be patient.. It's kind of monologue. After two years will see how noob I was :laughing:
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

<mark>Wow, endpoint is case sensitive!! Modified the curl.sh</mark><br>
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

#### 4. Time to create service layer:
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
<b>OOOOO Maa Go Turu Love!! </b><br>
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












