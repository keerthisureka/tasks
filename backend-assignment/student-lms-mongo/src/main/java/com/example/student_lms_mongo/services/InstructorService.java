package com.example.student_lms_mongo.services;

import com.example.student_lms_mongo.dto.InstructorDto;
import com.example.student_lms_mongo.entity.CourseStatus;

public interface InstructorService {
    public void addInstructor(String organizationId, InstructorDto dto);

    public void editInstructor(String organizationId, String instructorId, InstructorDto dto);

    public void deleteInstructor(String organizationId, String instructorId);

    public void registerForCourse(String instructorId, String courseId);

    public void deregisterFromCourse(String instructorId);

    public void courseUpdateStatusForStudent(String instructorId, String courseId, String studentId, CourseStatus status);
}
