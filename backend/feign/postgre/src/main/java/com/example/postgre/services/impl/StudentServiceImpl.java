package com.example.postgre.services.impl;

import com.example.postgre.dto.StudentDto;
import com.example.postgre.entity.Student;
import com.example.postgre.repository.StudentRepository;
import com.example.postgre.services.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    public StudentDto getStudentById(String id) {
        Student s = studentRepository.findById(id).get();
        StudentDto dto = new StudentDto();
        BeanUtils.copyProperties(s, dto);
        return dto;
    }
}
