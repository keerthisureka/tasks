package com.example.student_lms_postgre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.student_lms_postgre")
public class StudentLmsPostgreApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudentLmsPostgreApplication.class, args);
    }
}
