package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.repository.StudentRepository;
import com.example.student_lms_postgre.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
}
