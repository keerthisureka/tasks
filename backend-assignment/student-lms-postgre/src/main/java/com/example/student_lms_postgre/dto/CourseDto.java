package com.example.student_lms_postgre.dto;

import java.io.Serializable;

public class CourseDto implements Serializable {
    private String name;
    private double fee;

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
