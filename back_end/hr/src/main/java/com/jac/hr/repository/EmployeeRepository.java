package com.jac.hr.repository;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.model.Employee;
import com.jac.hr.rowmapper.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Employee saveEmployee(Employee employee){
        try {
            jdbcTemplate.update("Insert into employees (firstname, lastname, startDate, " +
                            "address, phone, departmentId, positionId, seniorityId) values (?,?,?,?,?,?,?,?)",
                    employee.getFirstname(), employee.getLastname(), employee.getStartDate(), employee.getAddress(),
                    employee.getPhone(), employee.getDepartmentId(), employee.getPositionId(), employee.getSeniorityId());

            //return the employee
            int id = jdbcTemplate.queryForObject(
                    "select max(id) from employees", Integer.class);
            employee.setId(id);
            return employee;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in saveEmployee ");
        }
    }


    public List<Employee> getAllEmployees(){
        try {
            return jdbcTemplate.query("SELECT * from employees",
                    (rs, rowNum) -> new Employee(rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("startDate"),
                            rs.getString("endDate"),
                            rs.getString("address"),
                            rs.getString("phone"),
                            rs.getInt("departmentId"),
                            rs.getInt("positionId"),
                            rs.getInt("seniorityId")));

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getAllEmployees ");
        }
    }

    public Employee getEmployeeByName(String lastname) {
        try {
            String sql = "SELECT * FROM employees WHERE lastname = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{lastname}, new EmployeeRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getEmployeeByName " + lastname);
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            String sql = "SELECT * FROM employees WHERE ID = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new EmployeeRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getEmployeeById " + id);
        }
    }

    public void updateEmployee(int id, Employee employee){
        try {
            jdbcTemplate.update("UPDATE employees set firstname=?, lastname=?, startDate=?," +
                            "endDate=?, address=?, phone=?, departmentId=?, positionId=?, seniorityId=? WHERE id=?",
                    employee.getFirstname(), employee.getLastname(), employee.getStartDate(), employee.getEndDate(),
                    employee.getAddress(), employee.getPhone(), employee.getDepartmentId(), employee.getPositionId(),
                    employee.getSeniorityId(), id);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in update " + id);
        }
    }

    public void deleteEmployeeById(int id){
        try {
            jdbcTemplate.update("delete from employees where id=?", id);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in deleteEmployeeById " + id);
        }
    }
}
