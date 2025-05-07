package com.sadnemous.demoSpringBootSvc.service;

import com.sadnemous.demoSpringBootSvc.model.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public interface IDemoSpringBootSvcService {

    public Employee getEmp(EmployeeInput employeeInput);
    public Employee getEmployee(EmployeeInput employeeInput);
    public List <Employee> getAllEmployee(EmployeeInput employeeInput);
}
