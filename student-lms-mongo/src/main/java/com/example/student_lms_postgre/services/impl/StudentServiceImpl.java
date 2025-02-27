package com.example.student_lms_postgre.services.impl;

import com.example.student_lms_postgre.entity.Course;
import com.example.student_lms_postgre.entity.CourseStatus;
import com.example.student_lms_postgre.entity.Student;
import com.example.student_lms_postgre.repository.CourseRepository;
import com.example.student_lms_postgre.repository.StudentRepository;
import com.example.student_lms_postgre.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    CourseRepository courseRepository;

    @Override
    public void enrollInCourse(String studentId, String courseId, CourseStatus status) {
        Student s = studentRepository.findOne(studentId);

        // s.getCourses().add(c);
        studentRepository.save(s);
    }

    @Override
    public void withdrawFromCourse(String studentId, String courseId) {

    }
}
