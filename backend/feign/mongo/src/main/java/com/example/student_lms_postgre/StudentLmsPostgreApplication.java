package com.example.student_lms_postgre;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class StudentLmsPostgreApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentLmsPostgreApplication.class, args);
    }

}
