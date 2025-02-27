package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.repository.InstructorRepository;
import com.example.student_lms_postgre.services.InstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InstructorServiceImpl implements InstructorService {
    @Autowired
    InstructorRepository instructorRepository;
}
