package com.example.ryzhkov.service;

import com.example.ryzhkov.dao.DisciplineDAO;
import com.example.ryzhkov.entity.Discipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DisciplineServiceImpl implements DisciplineService {

    private final DisciplineDAO disciplineDAO;

    @Autowired
    public DisciplineServiceImpl(DisciplineDAO disciplineDAO) {
        this.disciplineDAO = disciplineDAO;
    }

    @Override
    @Transactional
    public List<Discipline> getAllDisciplines() {
        return disciplineDAO.getAllDisciplines();
    }

    @Override
    @Transactional
    public Discipline saveDiscipline(Discipline discipline) {
        return disciplineDAO.saveDiscipline(discipline);
    }

    @Override
    @Transactional
    public Discipline getDiscipline(int id) {
        return disciplineDAO.getDiscipline(id);
    }

    @Override
    @Transactional
    public void deleteDiscipline(int id) {
        disciplineDAO.deleteDiscipline(id);
    }
}