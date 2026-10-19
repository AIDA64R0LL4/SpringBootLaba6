package com.example.ryzhkov.service;

import com.example.ryzhkov.entity.Discipline;
import java.util.List;

public interface DisciplineService {
    List<Discipline> getAllDisciplines();
    Discipline saveDiscipline(Discipline discipline);
    Discipline getDiscipline(int id);
    void deleteDiscipline(int id);
}