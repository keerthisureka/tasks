package com.example.student_lms_postgre.dto;

import java.io.Serializable;

public class CourseDto implements Serializable {
    private Long id;
    private String name;
    private double fee;

    @Override
    public String toString() {
        return id + " " + name + " " + fee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
