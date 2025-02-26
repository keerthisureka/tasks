package com.example.student_lms_postgre.services;

import com.example.student_lms_postgre.dto.InstructorDto;

import java.util.List;

public interface InstructorService {
    public List<InstructorDto> findAll();

    public InstructorDto getOne(Long id);
}
