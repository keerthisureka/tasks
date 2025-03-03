package com.example.student_lms_mongo.dto;

import java.io.Serializable;

public class OrganizationDto implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
