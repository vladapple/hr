package com.jac.hr.rowmapper;

import com.jac.hr.model.Employee;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getInt("id"));
        employee.setFirstname(rs.getString("firstname"));
        employee.setLastname(rs.getString("lastname"));
        employee.setStartDate(rs.getString("startDate"));
        employee.setEndDate(rs.getString("endDate"));
        employee.setAddress(rs.getString("address"));
        employee.setPhone(rs.getString("phone"));
        employee.setDepartmentId(rs.getInt("departmentId"));
        employee.setPositionId(rs.getInt("positionId"));
        employee.setSeniorityId(rs.getInt("seniorityId"));

        return employee;
    }
}

