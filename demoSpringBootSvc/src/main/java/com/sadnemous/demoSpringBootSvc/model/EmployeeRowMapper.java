package com.sadnemous.demoSpringBootSvc.model;

//import javax.swing.tree.RowMapper;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee (rs.getInt("ID"), rs.getString("FirstName"), rs.getString("LastName"));
        return employee;
    }
}
