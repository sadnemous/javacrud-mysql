package com.sadnemous.demoSpringBootSvc.controller;

import com.sadnemous.demoSpringBootSvc.model.Employee;
import org.springframework.boot.autoconfigure.orm.jpa.EntityManagerFactoryDependsOnPostProcessor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()

public class DemoSpringBootController {
    @GetMapping("/getempsvc")
    public ResponseEntity <Employee> getEmp ()
    {
        Employee emp = new Employee(31291, "Soumen", "Das");
        return ResponseEntity.ok(emp);

    }
}
