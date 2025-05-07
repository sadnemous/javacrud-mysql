package com.sadnemous.demoSpringBootSvc.service;

import com.sadnemous.demoSpringBootSvc.exceptions.NoEmployeeFoundException;
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
    public Employee getEmp(EmployeeInput input)
    {
        try {
            Employee employee = repo.getEmployeeFromDB(input);
            if (employee == null) {
                throw new NoEmployeeFoundException("from getempsvc, No employee found", null);
            }
            return employee;
        } catch (Exception ex) {
            throw new NoEmployeeFoundException("from getempsvc, No employee found", ex);

        }
    }

    @Override
    public Employee getEmployee(EmployeeInput employeeInput)
    {
        try {
            Employee employee = repo.getEmployeeFromDB(employeeInput);
            if (employee == null) {
                throw new NoEmployeeFoundException("No employee found", null);
            }
            return employee;
        } catch (Exception ex) {
            throw new NoEmployeeFoundException("No employee found for : " + employeeInput.getID(), ex);
            //throw ex;
        }
    }

    @Override
    public List <Employee> getAllEmployee(EmployeeInput employeeInput)
    {
        List <Employee> employees = repo.getAllEmployeeFromDB(employeeInput);
        if (employees == null) {
            throw new NoEmployeeFoundException("No employee found", null);
        }
        return employees;
    }
}
