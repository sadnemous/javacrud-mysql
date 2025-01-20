package com.sadnemous.demoSpringBootSvc.service;

import com.sadnemous.demoSpringBootSvc.model.*;
import com.sadnemous.demoSpringBootSvc.repository.DemoSpringBootSvcRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoSpringBootSvcServiceImpl implements IDemoSpringBootSvcService{
    @Autowired
    private DemoSpringBootSvcRepository repo;

    @Override
    public Employee getEmp()
    {
        Employee employee = repo.getEmpFromDB();
        return employee;
    }

    @Override
    public Employee getEmployee(EmployeeInput employeeInput)
    {
        Employee employee = repo.getEmployeeFromDB(employeeInput);
        return employee;
    }

    @Override
    public List <Employee> getAllEmployee(EmployeeInput employeeInput)
    {
        List <Employee> employees = repo.getAllEmployeeFromDB(employeeInput);
        return employees;
    }
}
