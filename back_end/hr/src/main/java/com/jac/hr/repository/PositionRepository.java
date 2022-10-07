package com.jac.hr.repository;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.model.Position;
import com.jac.hr.rowmapper.PositionRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PositionRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public Position savePosition(Position position){
        try {
            jdbcTemplate.update("Insert into positions (posName) values (?)",
                    position.getPosName());

            //return the position
            int posId= jdbcTemplate.queryForObject(
                    "select max(posId) from positions", Integer.class);
            position.setPosId(posId);
            return position;
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in savePosition ");
        }
    }


    public List<Position> getAllPositions(){
        try {
            return jdbcTemplate.query("SELECT * from positions",
                    (rs, rowNum) -> new Position(rs.getInt("posId"), rs.getString("posName")));
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getAllPositions ");
        }
    }

    public Position getPositionByName(String posName) {
        try {
            String sql = "SELECT * FROM positions WHERE posName = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{posName}, new PositionRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getPositionByName " + posName);
        }
    }

    public Position getPositionById(int posId) {
        try {
            String sql = "SELECT * FROM positions WHERE posId = ?";
            return jdbcTemplate.queryForObject(sql, new Object[]{posId}, new PositionRowMapper());

        }catch (Exception exc){
            throw new DatabaseException("an exception happened in getPositionById " + posId);
        }
    }

    public void updatePosition(int posId, Position position){
        try {
            jdbcTemplate.update("UPDATE positions set posName=? WHERE posId=?",
                    position.getPosName(), posId);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in update " + posId);
        }
    }

    public void deletePositionById(int posId){
        try {
            jdbcTemplate.update("delete from positions where posId=?", posId);
        }catch (Exception exc){
            throw new DatabaseException("an exception happened in deletePositionById " + posId);
        }
    }
}
