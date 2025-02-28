package com.example.student_lms_postgre.services;

import com.example.student_lms_postgre.dto.StudentDto;

import java.util.List;

public interface StudentService {
//    public void enrollInCourse(String studentId, String courseId, CourseStatus status);
//
//    public void withdrawFromCourse(String studentId, String courseId);

    public StudentDto getStudentById(String id);
}
