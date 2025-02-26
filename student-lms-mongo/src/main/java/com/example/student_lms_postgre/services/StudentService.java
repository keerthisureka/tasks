package com.example.student_lms_postgre.services;

import com.example.student_lms_postgre.dto.StudentDto;

import java.util.List;

public interface StudentService {
    public List<StudentDto> findAll();

    public StudentDto getOne(Long id);
}
