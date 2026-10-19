package com.example.ryzhkov.dao;

import com.example.ryzhkov.entity.Discipline;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Slf4j
@Repository
public class DisciplineDAOImpl implements DisciplineDAO {

    private final EntityManager entityManager;

    @Autowired
    public DisciplineDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Discipline> getAllDisciplines() {
        Query query = entityManager.createQuery("from Discipline");
        List<Discipline> allDisciplines = query.getResultList();
        log.info("Получен список всех дисциплин: " + allDisciplines);
        return allDisciplines;
    }

    @Override
    public Discipline saveDiscipline(Discipline discipline) {
        return entityManager.merge(discipline);
    }

    @Override
    public Discipline getDiscipline(int id) {
        return entityManager.find(Discipline.class, id);
    }

    @Override
    public void deleteDiscipline(int id) {
        Query query = entityManager.createQuery("delete from Discipline where id =:disciplineId");
        query.setParameter("disciplineId", id);
        query.executeUpdate();
    }
}
