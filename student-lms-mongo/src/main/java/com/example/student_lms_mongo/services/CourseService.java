package com.example.student_lms_mongo.services;

import com.example.student_lms_mongo.dto.CourseDto;

public interface CourseService {
    public void addCourse(CourseDto dto);

    public void deleteCourse(String id);

    public int getCountOfStudentsInEachCourse(String courseId);
}
