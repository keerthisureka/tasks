package com.example.student_lms_mongo.services;

import com.example.student_lms_mongo.dto.StudentCourseDto;
import com.example.student_lms_mongo.dto.StudentDto;
import com.example.student_lms_mongo.entity.CourseStatus;

import java.util.List;

public interface StudentService {
    public void addStudent(StudentDto dto);

    public void editStudent(String id, StudentDto dto);

    public void deleteStudent(String studentId);

    public void enrollInCourse(String studentId, String courseId, CourseStatus status);

    public void withdrawFromCourse(String studentId, String courseId);

    public List<StudentCourseDto> courseProgress(String studentId);
}
