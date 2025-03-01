package com.example.student_lms_mongo.dto;

import java.io.Serializable;
import java.util.List;

public class OrganizationDto implements Serializable {
    private String id;
    private String name;
    private List<String> studentIds;
    private List<String> instructorIds;
    private List<String> courseIds;

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

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }

    public List<String> getInstructorIds() {
        return instructorIds;
    }

    public void setInstructorIds(List<String> instructorIds) {
        this.instructorIds = instructorIds;
    }

    public List<String> getCourseIds() {
        return courseIds;
    }

    public void setCourseIds(List<String> courseIds) {
        this.courseIds = courseIds;
    }
}
