package com.example.crudapp.repository;

import com.example.crudapp.model.Employee;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {
    private final JdbcTemplate jdbcTemplate;

    public EmployeeRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Employee> findAll() {
        return jdbcTemplate.query("SELECT * FROM employee", (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFname(rs.getString("fname"));
            employee.setLname(rs.getString("lname"));
            return employee;
        });
    }

    public Employee findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM employee WHERE id = ?", new Object[]{id}, (rs, rowNum) -> {
            Employee employee = new Employee();
            employee.setId(rs.getInt("id"));
            employee.setFname(rs.getString("fname"));
            employee.setLname(rs.getString("lname"));
            return employee;
        });
    }

    public int save(Employee employee) {
        return jdbcTemplate.update("INSERT INTO employee (id, fname, lname) VALUES (?, ?, ?)",
                employee.getId(), employee.getFname(), employee.getLname());
    }

    public int update(Employee employee) {
        return jdbcTemplate.update("UPDATE employee SET fname = ?, lname = ? WHERE id = ?",
                employee.getFname(), employee.getLname(), employee.getId());
    }

    public int deleteById(int id) {
        return jdbcTemplate.update("DELETE FROM employee WHERE id = ?", id);
    }
}
