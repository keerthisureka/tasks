package com.example.student_lms_postgre.services;

import com.example.student_lms_postgre.dto.CourseDto;

import java.util.List;

public interface CourseService {
    public List<CourseDto> findAll();

    public CourseDto getOne(Long id);
}
