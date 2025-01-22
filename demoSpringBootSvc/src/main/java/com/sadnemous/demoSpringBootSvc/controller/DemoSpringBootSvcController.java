package com.sadnemous.demoSpringBootSvc.controller;

import com.sadnemous.demoSpringBootSvc.model.Employee;
import com.sadnemous.demoSpringBootSvc.model.EmployeeInput;
import com.sadnemous.demoSpringBootSvc.service.IDemoSpringBootSvcService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping()
@Tag(name = "DemoSpringBootSvcController", description = "Demo Spring Boot Service Controller")
public class DemoSpringBootSvcController {

    @Autowired
    private IDemoSpringBootSvcService svcService;

    @GetMapping("/getempsvc")
    public ResponseEntity <Employee> getEmp ()
    {
        //Employee emp = new Employee(11089, "Soumen", "Das");
        Employee emp = svcService.getEmp();
        return ResponseEntity.ok(emp);

    }

    @PostMapping("/employee")
    public ResponseEntity <Employee> GetEmployee (@RequestBody EmployeeInput employeeInput)
    {
        Employee employee = svcService.getEmployee(employeeInput);
        return ResponseEntity.ok(employee);
    }

    @PostMapping("/allemployees")
    public ResponseEntity <List <Employee>> GetAllEmployee (@RequestBody EmployeeInput employeeInput)
    {
        List <Employee> employees = svcService.getAllEmployee(employeeInput);
        return ResponseEntity.ok(employees);
    }
}
