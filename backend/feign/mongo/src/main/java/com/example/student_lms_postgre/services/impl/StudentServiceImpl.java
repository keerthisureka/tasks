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

//    @Override
//    public void enrollInCourse(String studentId, String courseId, CourseStatus status) {
//        Student s = studentRepository.findOne(studentId);
//
//        // s.getCourses().add(c);
//        studentRepository.save(s);
//    }
//
//    @Override
//    public void withdrawFromCourse(String studentId, String courseId) {
//
//    }

    public StudentDto getStudentById(String id) {
        Student s = studentRepository.findById(id).get();
        StudentDto dto = new StudentDto();
        BeanUtils.copyProperties(s, dto);
        return dto;
    }
}
