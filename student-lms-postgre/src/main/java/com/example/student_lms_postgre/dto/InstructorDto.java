package com.example.student_lms_postgre.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class InstructorDto {
    private Long id;
    private String name;
    private LocalDate dob;

    @Override
    public String toString() {
        return id + " " + name + " " + dob;
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }
}
