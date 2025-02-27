package com.example.student_lms_postgre.entity;

import org.springframework.data.annotation.Id;

import java.time.LocalDate;

public class Student {
    @Id
    private String id;
    private String name;
    private LocalDate dob;
}
