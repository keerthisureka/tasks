package com.example.student_lms_postgre.dto;

import lombok.Data;

import java.util.Date;

@Data
public class InstructorDto {
    private Long id;
    private String name;
    private Date dob;

    @Override
    public String toString() {
        return id + " " + name + " " + dob;
    }
}
