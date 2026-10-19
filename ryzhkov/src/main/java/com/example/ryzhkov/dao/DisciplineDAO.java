package com.example.ryzhkov.dao;

import com.example.ryzhkov.entity.Discipline;
import java.util.List;

public interface DisciplineDAO {
    List<Discipline> getAllDisciplines();
    Discipline saveDiscipline(Discipline discipline);
    Discipline getDiscipline(int id);
    void deleteDiscipline(int id);
}