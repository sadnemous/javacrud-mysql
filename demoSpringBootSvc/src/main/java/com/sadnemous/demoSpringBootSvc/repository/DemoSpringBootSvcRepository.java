package com.sadnemous.demoSpringBootSvc.repository;

import com.sadnemous.demoSpringBootSvc.model.Employee;
import com.sadnemous.demoSpringBootSvc.model.EmployeeInput;
import com.sadnemous.demoSpringBootSvc.model.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DemoSpringBootSvcRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Employee getEmpFromDB() {
        String sql = "SELECT * FROM EMPLOYEE WHERE id=1";
        Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper());
        //Employee employee = new Employee(9212, "Saba", "Liam");
        return (employee);
    }

    public Employee getEmployeeFromDB(EmployeeInput employeeInput) {
        String sql = "SELECT * FROM EMPLOYEE WHERE id=?";
        Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), employeeInput.getID());
        return (employee);
    }

    public List<Employee> getAllEmployeeFromDB(EmployeeInput employeeInput) {
        String sql = "SELECT * FROM EMPLOYEE WHERE id>=?";
        List <Employee> employees = jdbcTemplate.query(sql, new EmployeeRowMapper(), employeeInput.getID());
        return (employees);
    }
}
