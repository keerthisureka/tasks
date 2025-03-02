package com.example.student_lms_mongo.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class StudentDto implements Serializable {
    private String name;
    private LocalDate dob;

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
}
