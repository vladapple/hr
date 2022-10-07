package com.jac.hr.service;

import com.jac.hr.exception.DatabaseException;
import com.jac.hr.exception.SeniorityExistException;
import com.jac.hr.exception.SeniorityNotFoundException;
import com.jac.hr.model.Seniority;
import com.jac.hr.repository.SeniorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeniorityService {

    @Autowired
    SeniorityRepository seniorityRepository;

    public List<Seniority> getAllSeniorities() {

        return seniorityRepository.getAllSeniorities();
    }

    public Seniority getSeniorityByName(String senName) {
        try {
            return seniorityRepository.getSeniorityByName(senName);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Seniority getSeniorityById(int senId) {
        try {
            return seniorityRepository.getSeniorityById(senId);
        } catch (DatabaseException exc) {
            return null;
        }
    }

    public Seniority saveSeniority(Seniority seniority){
        if (getSeniorityByName(seniority.getSenName()) == null) {
            return seniorityRepository.saveSeniority(seniority);
        } else {
            throw new SeniorityExistException("the seniority with the same name exists");
        }
    }

    public Seniority updateSeniority(int senId, Seniority seniority) {
        Seniority fetchedSeniority = getSeniorityById(senId);
        if (fetchedSeniority == null) {
            throw new SeniorityNotFoundException("the seniority does not exists " + senId);
        }
        seniorityRepository.updateSeniority(senId, seniority);

        return seniority;
    }

    public void deleteSeniority(int senId) {
        Seniority fetchedSeniority = getSeniorityById(senId);
        if (fetchedSeniority == null) {
            throw new SeniorityNotFoundException("the seniority does not exists " + senId);
        }
        seniorityRepository.deleteSeniorityById(senId);
    }

}
