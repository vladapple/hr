package com.jac.hr.rowmapper;

import com.jac.hr.model.Seniority;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class SeniorityRowMapper implements RowMapper<Seniority> {

    @Override
    public Seniority mapRow(ResultSet rs, int rowNum) throws SQLException {
        Seniority seniority = new Seniority();
        seniority.setSenId(rs.getInt("senId"));
        seniority.setSenName(rs.getString("senName"));

        return seniority;
    }
}

