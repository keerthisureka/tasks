package com.example.postgre.services;

import com.example.postgre.dto.StudentDto;

public interface StudentService {
    public StudentDto getStudentById(String id);
}
