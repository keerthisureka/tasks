package com.example.student_lms_postgre.services;

import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.CourseStatus;

import java.util.*;

public interface StudentService {
    public List<StudentDto> findAll();

    public StudentDto getOne(Long id);

    public void enrollInCourse(Long studentId, Long courseId, CourseStatus status);

    public void withdrawFromCourse(Long studentId, Long courseId);

    public List<Map<String, Object>> courseProgress(Long studentId);
}
