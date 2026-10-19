package com.example.ryzhkov.service;

import com.example.ryzhkov.entity.Student;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudent(int id);
    void deleteStudent(int id);
}