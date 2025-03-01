package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.repository.CourseRepository;
import com.example.student_lms_postgre.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseRepository courseRepository;


}
