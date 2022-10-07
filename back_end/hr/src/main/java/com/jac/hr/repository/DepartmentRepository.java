package com.jac.hr.repository;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.model.Department;
import com.jac.hr.rowmapper.DepartmentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DepartmentRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Department saveDepartment(Department department){
        try {
            jdbcTemplate.update("Insert into departments (depName, depAddress, depPhone) values (?,?,?)",
                    department.getDepName(), department.getDepAddress(), department.getDepPhone());

            //return the department
            int depId= jdbcTemplate.queryForObject(
                    "select max(depId) from departments", Integer.class);
            department.setDepId(depId);
            return department;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in saveDepartment ");
        }
    }


    public List<Department> getAllDepartments(){
        try {
            return jdbcTemplate.query("SELECT * from departments",
                    (rs, rowNum) -> new Department(rs.getInt("depId"),
                            rs.getString("depName"),
                            rs.getString("depAddress"),
                            rs.getString("depPhone")));
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getAllDepartments ");
        }
    }

    public Department getDepartmentByName(String depName) {
        try {
            String sql = "SELECT * FROM departments WHERE depName = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{depName}, new DepartmentRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getDepartmentByName " + depName);
        }
    }

    public Department getDepartmentById(int depId) {
        try {
            String sql = "SELECT * FROM departments WHERE depId = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{depId}, new DepartmentRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getDepartmentById " + depId);
        }
    }

    public void updateDepartment(int depId, Department department){
        try {
            jdbcTemplate.update("UPDATE departments set depName=?, depAddress=?, depPhone=? WHERE depId=?",
                    department.getDepName(), department.getDepAddress(), department.getDepPhone(), depId);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in update " + depId);
        }
    }

    public void deleteDepartmentById(int depId){
        try {
            jdbcTemplate.update("delete from departments where depId=?", depId);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in deleteDepartmentById " + depId);
        }
    }
}
