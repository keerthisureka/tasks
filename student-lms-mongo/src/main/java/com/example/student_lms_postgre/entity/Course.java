package com.example.student_lms_postgre.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

@Document(collection = Course.COLLECTION_NAME)
public class Course implements Serializable {
    public static final String COLLECTION_NAME="course";

    @Id
    private String id;
    private String name;
    private double fee;
    private List<Student> studentIds;
    private List<Instructor> instructorIds;

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
}
