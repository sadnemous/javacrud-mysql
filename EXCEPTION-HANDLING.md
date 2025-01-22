Whenever we think of handling exceptions at any level of our code we fall under writing everywhere try catch block in our code and then after some days when we try to go through our code we find most of the code is filled with handling exceptions. This degrades the readability of our code and also duplication of a lot of logger messages which we can easily avoid. Here we will try to learn the powerful feature provided by Spring Boot to avoid these duplications and improve readability of code while handling exceptions in our application.

## Overview
As we all know exception handling is the most important and crucial thing in SpringBoot Rest APIs, which helps us to perform conditional and unconditional checking for our code and handle any kind of exception in a proper way. Along with its benefits it also complicates the code and makes the code not easily readable to the unknown users.

So we need to have a common place where we can manage and handle all kinds of exceptions and send the respective error code for the API response depending on the exception types.

In this blog we will try to know a simple way which will make our code be in better format related to handling of exceptions provided in SpringBoot.

## Description
SpringBoot provides a very powerful annotation called `@ControllerAdvice` under package `org.springframework.web.bind.annotation`. This annotation makes our life easy to handle all kinds of exceptions at a central place in our application. We don’t need to catch any exception at each method or class separately instead you can just throw the exception from the method and then it will be caught under the central exception handler class annotated by `@ControllerAdvice`. Any class annotated with `@ControllerAdvice` will become a controller-advice class which will be responsible for handling exceptions. Under this class we make use of annotations provided as `@ExceptionHandler`, `@ModelAttribute`, `@InitBinder`.

`@ControllerAdvice` is a specialisation of the `@Component` annotation which allows handling exceptions across the whole application in one global handling component. It can be viewed as an interceptor of exceptions thrown by methods annotated with `@RequestMapping` and similar.

`ResponseEntityExceptionHandler` is a convenient base class for `@ControllerAdvice` classes that wish to provide centralised exception handling across all `@RequestMapping` methods through `@ExceptionHandler` methods. It provides methods for handling internal Spring MVC exceptions. It returns a `ResponseEntity` in contrast to `DefaultHandlerExceptionResolver` which returns a `Model`And`View`.

Exception handling methods annotated with `@ExceptionHandler` will catch the exception thrown by the declared class and we can perform various things whenever we come through the related type exceptions. We can catch various exceptions and throw various http status codes depending on the exception which we need to handle. Below example illustrates how we can catch various exceptions and send respective http status codes accordingly.
```java
  @ExceptionHandler(value = { DataNotFoundException.class })

  public ResponseEntity<ResponseDTO<Object>> dataNotFoundException(InValidDataException ex) {

  ResponseDTO<Object> response = new ResponseDTO<Object>(HttpStatus.BAD_REQUEST,Constants.STATUS_FAIL,ex.getLocalizedMessage(),false);

  LOGGER.error(“Data not found exception: “,ex);

  return new ResponseEntity<ResponseDTO<Object>>(response ,HttpStatus.BAD_REQUEST);

  }
```

```java
  @ExceptionHandler(value = { NetworkException.class })

  public ResponseEntity<ResponseDTO<Object>> networkException(Exception ex) {

  ResponseDTO<Object> response= new ResponseDTO<Object>(HttpStatus.INTERNAL_SERVER_ERROR,Constants.STATUS_FAIL,ex.getLocalizedMessage(),false);

  LOGGER.error(“Network Exception: “,ex);

  return new ResponseEntity<ResponseDTO<Object>>(response,HttpStatus.INTERNAL_SERVER_ERROR);

}
```

`@ControllerAdvice` constructor comes with some special arguments which allows you to scan only the related portion of your application and handle only those exceptions thrown by the respective classes mentioned in the constructor. By default it will scan and handle all the classes in your application. Below are some types which we can use to restrict only specific classes to handle exceptions.

1) <mark>annotations</mark> — Controllers that are annotated with the mentioned annotations will be assisted by the `@ControllerAdvice` annotated class and are eligible for exception of those classes

eg. `@ControllerAdvice(annotations = RestController.class)` — Here the exception helper annotated by `@ControllerAdvice` will catch all the exceptions thrown by the `@RestController` annotation classes.

2) <mark>basePackages</mark> — By Specifying the packages which we want to scan and handling exceptions for the same.

eg. `@ControllerAdvice(basePackages = “org.example.controllers”)` — This will only scan call the mentioned package and handle the exceptions for the same.

3) <mark>assignableTypes</mark> — This arguments will make sure to scan and handle the exceptions from the mentioned classes

eg. `@ControllerAdvice(assignableTypes = {ControllerInterface.class, AbstractController.class})`

Before Using `@ControllerAdvice`
In the below code snippet we see there are many duplications of lines as well the controller code is not able to be easily readable because of multiple try and catch blocks in each API.
```java
@RestController
@RequestMapping(path = “/employees”)
public class EmployeeController {

private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

private EmployeeDao employeeDao;

@GetMapping(path=”/{employeeId}”, produces = “application/json”)
public ResponseEntity<Employee> getEmployees(@PathVariable Long employeeId) {
ResponseEntity<Employee> response = null;
try {
if(null==employeeId || positionId.equals(0L)) {
throw new InvalidInputException(“Employee Id is not valid”);
}
employee = employeeDao.getEmployeeDetails(employeeId);
response = new ResponseEntity<Employee>(employee,HttpStatus.OK);
}
catch(InvalidInputException e) {
Logger.error(“Invalid Input:”,e.getMessage());
response = new ResponseEntity<Employee>(employee,HttpStatus.BAD_REQUEST);
}
catch(BusinessException e) {
Logger.error(“Business Exception:”,e.getMessage());
response = new ResponseEntity<Employee>(employee,HttpStatus.INTERNAL_SERVER_ERROR);
}
catch(Exception e) {
Logger.error(“System Error:”,e.getMessage());
response = new ResponseEntity<Employee>(employee,HttpStatus.INTERNAL_SERVER_ERROR);
}
return response;
}

}
```
After Using `@ControllerAdvice`

Below code snippet makes the code easily readable and also it reduces duplications of lines as well.

```java
@RestController
@RequestMapping(path = “/employees”)
public class EmployeeController {

private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

@GetMapping(path=”/{employeeId}”, produces = “application/json”)
public ResponseEntity<Employee> getEmployees(@PathVariable Long employeeId) {
if(null==employeeId || positionId.equals(0L)) {
throw new InvalidInputException(“Employee Id is not valid”);
}
Employee employee = employeeDao.getEmployeeDetails(employeeId);
return new ResponseEntity<Employee>(employee,HttpStatus.OK);;
}

}

```
