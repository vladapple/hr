package com.jac.hr.repository;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.model.Seniority;
import com.jac.hr.rowmapper.SeniorityRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SeniorityRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Seniority saveSeniority(Seniority seniority){
        try {
            jdbcTemplate.update("Insert into seniorities (senName) values (?)",
                    seniority.getSenName());

            //return the seniority
            int senId= jdbcTemplate.queryForObject(
                    "select max(senId) from seniorities", Integer.class);
            seniority.setSenId(senId);
            return seniority;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in saveSeniority ");
        }
    }


    public List<Seniority> getAllSeniorities(){
        try {
            return jdbcTemplate.query("SELECT * from seniorities",
                    (rs, rowNum) -> new Seniority(rs.getInt("senId"), rs.getString("senName")));
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getAllSeniorities ");
        }
    }

    public Seniority getSeniorityByName(String senName) {
        try {
            String sql = "SELECT * FROM seniorities WHERE senName = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{senName}, new SeniorityRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getSeniorityByName " + senName);
        }
    }

    public Seniority getSeniorityById(int senId) {
        try {
            String sql = "SELECT * FROM seniorities WHERE senId = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{senId}, new SeniorityRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getSeniorityById " + senId);
        }
    }

    public void updateSeniority(int senId, Seniority seniority){
        try {
            jdbcTemplate.update("UPDATE seniorities set senName=? WHERE senId=?",
                    seniority.getSenName(), senId);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in update " + senId);
        }
    }

    public void deleteSeniorityById(int senId){
        try {
            jdbcTemplate.update("delete from seniorities where senId=?", senId);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in deleteSeniorityById " + senId);
        }
    }
}
