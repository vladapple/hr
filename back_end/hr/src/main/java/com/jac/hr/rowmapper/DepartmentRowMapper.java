package com.jac.hr.rowmapper;

import com.jac.hr.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class DepartmentRowMapper implements RowMapper<Department> {

    @Override
    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
        Department department = new Department();
        department.setDepId(rs.getInt("depId"));
        department.setDepName(rs.getString("depName"));
        department.setDepAddress(rs.getString("depAddress"));
        department.setDepPhone(rs.getString("depPhone"));

        return department;
    }
}

