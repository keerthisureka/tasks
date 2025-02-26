package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.dto.StudentDto;
import com.example.student_lms_postgre.entity.Student;
import com.example.student_lms_postgre.repository.StudentRepository;
import com.example.student_lms_postgre.services.StudentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDto> findAll() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> dto = new ArrayList<>();
        for (Student s : students) {
            StudentDto temp = new StudentDto();
            BeanUtils.copyProperties(s, temp);
            dto.add(temp);
        }
        return dto;
    }

    public StudentDto getOne(Long id) {
        Student s = studentRepository.getOne(id);
        StudentDto dto = new StudentDto();
        BeanUtils.copyProperties(s, dto);
        return dto;
    }
}
