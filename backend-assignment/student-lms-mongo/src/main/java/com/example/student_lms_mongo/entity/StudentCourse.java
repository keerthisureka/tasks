package com.example.student_lms_mongo.entity;

import java.io.Serializable;

public class StudentCourse implements Serializable {
    private String courseId;
    private CourseStatus status;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public CourseStatus getStatus() {
        return status;
    }

    public void setStatus(CourseStatus status) {
        this.status = status;
    }
}
