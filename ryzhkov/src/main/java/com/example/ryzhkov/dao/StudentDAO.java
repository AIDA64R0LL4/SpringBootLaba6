package com.example.ryzhkov.dao;

import com.example.ryzhkov.entity.Student;
import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudent(int id);
    void deleteStudent(int id);
}