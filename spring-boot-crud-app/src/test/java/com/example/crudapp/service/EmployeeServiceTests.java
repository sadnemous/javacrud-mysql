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
