package com.example.student_lms_mongo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = Course.COLLECTION_NAME)
public class Course {
    public static final String COLLECTION_NAME="courses";

    @Id
    private String id;
    private String name;
    private double fee;
    private String organizationId;
    private List<String> instructorIds;
    private List<String> studentIds;

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

    public String getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(String organizationId) {
        this.organizationId = organizationId;
    }

    public List<String> getInstructorIds() {
        return instructorIds;
    }

    public void setInstructorIds(List<String> instructorIds) {
        this.instructorIds = instructorIds;
    }

    public List<String> getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(List<String> studentIds) {
        this.studentIds = studentIds;
    }
}
