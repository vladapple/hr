package com.jac.hr.rowmapper;

import com.jac.hr.model.Position;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PositionRowMapper implements RowMapper<Position> {

    @Override
    public Position mapRow(ResultSet rs, int rowNum) throws SQLException {
        Position position = new Position();
        position.setPosId(rs.getInt("posId"));
        position.setPosName(rs.getString("posName"));

        return position;
    }
}

