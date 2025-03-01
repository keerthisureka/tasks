package com.example.student_lms_mongo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class StudentDto implements Serializable {
    private String id;
    private String name;
    private LocalDate dob;
    private String organizationId;
    private List<StudentCourseDto> studentCourseDtos;

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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public List<StudentCourseDto> getStudentCourseDtos() {
        return studentCourseDtos;
    }

    public void setStudentCourseDtos(List<StudentCourseDto> studentCourseDtos) {
        this.studentCourseDtos = studentCourseDtos;
    }
}
