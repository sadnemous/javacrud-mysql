package com.sadnemous.demoSpringBootSvc.repository;

import com.sadnemous.demoSpringBootSvc.exceptions.DemoExceptionHandler;
import com.sadnemous.demoSpringBootSvc.exceptions.NoEmployeeFoundException;
import com.sadnemous.demoSpringBootSvc.model.Employee;
import com.sadnemous.demoSpringBootSvc.model.EmployeeInput;
import com.sadnemous.demoSpringBootSvc.model.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
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
        try {
            Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), employeeInput.getID());
            return (employee);
        } catch (DataAccessException e) {
            throw new NoEmployeeFoundException(e.getMessage(),e);
            /*
            //I need to explore more on this
            // Handle the exception
            if (e instanceof EmptyResultDataAccessException) {
                // Handle the case where no result is found
                return null;
            } else if (e instanceof BadSqlGrammarException) {
                // Handle the case of invalid SQL
                return null;
            } else {
                // Handle other data access exceptions
                return null;
            }
             */
        }
    }

    public List<Employee> getAllEmployeeFromDB(EmployeeInput employeeInput) {
        try {
            String sql = "SELECT * FROM EMPLOYEE WHERE id>=?";
            List <Employee> employees = jdbcTemplate.query(sql, new EmployeeRowMapper(), employeeInput.getID());
            if (employees.isEmpty()) {
                throw new NoEmployeeFoundException("No employee found for id >= " + employeeInput.getID(), null);
            }
            return (employees);
        } catch (DataAccessException e) {
            //I need to explore more on this
            // Handle the exception
            if (e instanceof EmptyResultDataAccessException) {
                // Handle the case where no result is found
                //return null;
                throw e;
            } else if (e instanceof BadSqlGrammarException) {
                // Handle the case of invalid SQL
                //return null;
                throw e;
            } else {
                // Handle other data access exceptions
                return null;
            }
        }
    }
}
