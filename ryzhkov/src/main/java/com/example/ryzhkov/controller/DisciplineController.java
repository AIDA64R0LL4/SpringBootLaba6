package com.example.ryzhkov.controller;

import com.example.ryzhkov.entity.Discipline;
import com.example.ryzhkov.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DisciplineController {

    private final DisciplineService disciplineService;

    @Autowired
    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @GetMapping("/disciplines")
    public ResponseEntity<List<Discipline>> allDisciplines() {
        List<Discipline> disciplines = disciplineService.getAllDisciplines();
        if (disciplines.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(disciplines, HttpStatus.OK);
    }

    @GetMapping("/disciplines/{id}")
    public ResponseEntity<?> getDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return new ResponseEntity<>("Дисциплина с id " + id + " не найдена", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(discipline, HttpStatus.OK);
    }

    @PostMapping("/disciplines")
    public ResponseEntity<Discipline> saveDiscipline(@RequestBody Discipline discipline) {
        Discipline savedDiscipline = disciplineService.saveDiscipline(discipline);
        return new ResponseEntity<>(savedDiscipline, HttpStatus.CREATED);
    }

    @PutMapping("/disciplines")
    public ResponseEntity<Discipline> updateDiscipline(@RequestBody Discipline discipline) {
        Discipline updatedDiscipline = disciplineService.saveDiscipline(discipline);
        return new ResponseEntity<>(updatedDiscipline, HttpStatus.OK);
    }

    @DeleteMapping("/disciplines/{id}")
    public ResponseEntity<String> deleteDiscipline(@PathVariable("id") int id) {
        Discipline discipline = disciplineService.getDiscipline(id);
        if (discipline == null) {
            return new ResponseEntity<>("Дисциплина с id " + id + " не найдена", HttpStatus.NOT_FOUND);
        }
        disciplineService.deleteDiscipline(id);
        return new ResponseEntity<>("Дисциплина с id " + id + " успешно удалена", HttpStatus.OK);
    }
}