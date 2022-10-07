package com.jac.hr.rowmapper;

import com.jac.hr.model.EmployeeJoin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class EmployeeJoinRowMapper implements RowMapper<EmployeeJoin> {

    @Override
    public EmployeeJoin mapRow(ResultSet rs, int rowNum) throws SQLException {
        EmployeeJoin employeeJoin = new EmployeeJoin();
        employeeJoin.setId(rs.getInt("id"));
        employeeJoin.setFirstname(rs.getString("firstname"));
        employeeJoin.setLastname(rs.getString("lastname"));
        employeeJoin.setDepName(rs.getString("depName"));
        employeeJoin.setDepPhone(rs.getString("depPhone"));
        employeeJoin.setPosName(rs.getString("posName"));
        employeeJoin.setSenName(rs.getString("senName"));

        return employeeJoin;
    }
}

