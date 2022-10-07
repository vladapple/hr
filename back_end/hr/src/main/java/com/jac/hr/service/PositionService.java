package com.jac.hr.service;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.exception.PositionExistException;
import com.jac.hr.exception.PositionNotFoundException;
import com.jac.hr.model.Position;
import com.jac.hr.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionService {

    @Autowired
    PositionRepository positionRepository;

    public List<Position> getAllPositions() {

        return positionRepository.getAllPositions();
    }

    public Position getPositionByName(String posName) {
        try {
            return positionRepository.getPositionByName(posName);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Position getPositionById(int posId) {
        try {
            return positionRepository.getPositionById(posId);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Position savePosition(Position position){
        if (getPositionByName(position.getPosName()) == null) {
            return positionRepository.savePosition(position);
        } else {
            throw new PositionExistException("the position with the same name exists");
        }
    }

    public Position updatePosition(int posId, Position position) {
        Position fetchedPosition = getPositionById(posId);
        if (fetchedPosition == null) {
            throw new PositionNotFoundException("the position does not exists " + posId);
        }
        positionRepository.updatePosition(posId, position);

        return position;
    }

    public void deletePosition(int posId) {
        Position fetchedPosition = getPositionById(posId);
        if (fetchedPosition == null) {
            throw new PositionNotFoundException("the position does not exists " + posId);
        }
        positionRepository.deletePositionById(posId);
    }


}
