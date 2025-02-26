package com.example.student_lms_postgre.dto;

import lombok.Data;

@Data
public class CourseDto {
    private Long id;
    private String name;
    private double fee;

    @Override
    public String toString() {
        return id + " " + name + " " + fee;
    }
}
