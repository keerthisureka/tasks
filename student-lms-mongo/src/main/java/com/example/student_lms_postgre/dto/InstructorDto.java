package com.example.student_lms_postgre.dto;

import com.example.student_lms_postgre.entity.Course;

import java.time.LocalDate;

public class InstructorDto {
    private String id;
    private String name;
    private LocalDate dob;
    private Course courseId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Course getCourseId() {
        return courseId;
    }

    public void setCourseId(Course courseId) {
        this.courseId = courseId;
    }
}
