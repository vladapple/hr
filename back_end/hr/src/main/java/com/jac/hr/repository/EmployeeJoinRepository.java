package com.jac.hr.repository;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.model.EmployeeJoin;
import com.jac.hr.rowmapper.EmployeeJoinRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeJoinRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;


    public List<EmployeeJoin> getAllEmployeesJoined(){
        try {
            String sql = "select id, firstname, lastname, depName, depPhone, posName, senName\n" +
                         "from employees e, departments d, positions p, seniorities s\n" +
                         "where e.departmentId = d.depId and e.positionId = p.posId and e.seniorityId = s.senId";
            return jdbcTemplate.query(sql, (rs, rowNum) -> new EmployeeJoin(
                            rs.getInt("id"),
                            rs.getString("firstname"),
                            rs.getString("lastname"),
                            rs.getString("depName"),
                            rs.getString("depPhone"),
                            rs.getString("posName"),
                            rs.getString("senName")));

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getAllEmployeesJoined");
        }
    }

    public List<EmployeeJoin> getAllManagers(){
        try {
            String sql = "select id, firstname, lastname, depName, depPhone, posName, senName\n" +
                    "from employees e, departments d, positions p, seniorities s\n" +
                    "where e.departmentId = d.depId and e.positionId = p.posId and e.seniorityId = s.senId " +
                    "and posName='Manager'";
            return jdbcTemplate.query(sql, (rs, rowNum) -> new EmployeeJoin(
                    rs.getInt("id"),
                    rs.getString("firstname"),
                    rs.getString("lastname"),
                    rs.getString("depName"),
                    rs.getString("depPhone"),
                    rs.getString("posName"),
                    rs.getString("senName")));

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getAllManagers");
        }
    }

    public EmployeeJoin getManagerByDepName(String depName) {
        try {
            String sql = "select id, firstname, lastname, depName, depPhone, posName, senName\n" +
                    "from employees e, departments d, positions p, seniorities s\n" +
                    "where e.departmentId = d.depId and e.positionId = p.posId and e.seniorityId = s.senId " +
                    "and posName='Manager' and depName=?";
            return jdbcTemplate.queryForObject(sql, new Object[]{depName}, new EmployeeJoinRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getManagerByDepName " + depName);
        }
    }
}
