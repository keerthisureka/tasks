package com.example.student_lms_postgre.dto;

import com.example.student_lms_postgre.entity.Instructor;
import com.example.student_lms_postgre.entity.Student;

import java.util.List;

public class CourseDto {
    private String id;
    private String name;
    private double fee;
    private List<Student> studentIds;
    private List<Instructor> instructorIds;

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

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public List<Student> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<Student> studentIds) {
        this.studentIds = studentIds;
    }

    public List<Instructor> getInstructorIds() {
        return instructorIds;
    }

    public void setInstructorIds(List<Instructor> instructorIds) {
        this.instructorIds = instructorIds;
    }
}
