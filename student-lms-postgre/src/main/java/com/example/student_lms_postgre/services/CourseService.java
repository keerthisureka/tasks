package com.example.student_lms_postgre.services;

import com.example.student_lms_postgre.dto.CourseDto;
import com.example.student_lms_postgre.dto.InstructorDto;
import com.example.student_lms_postgre.dto.StudentDto;

import java.util.List;

public interface CourseService {
    public void addCourse(String organizationId, CourseDto dto);

    public void deleteCourse(String organizationId, String courseId);

    public int getCountOfStudentsInEachCourse(String organizationId, String courseId);

    public List<InstructorDto> detailsOfInstructorsForEachCourse(String organizationId, String courseId);

    public List<StudentDto> detailsOfStudentsForEachCourse(String organizationId, String courseId);

    public CourseDto detailsOfCourse(String organizationId, String courseId);
}
