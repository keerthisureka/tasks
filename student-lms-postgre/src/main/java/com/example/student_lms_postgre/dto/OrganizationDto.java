package com.example.student_lms_postgre.dto;

import java.io.Serializable;

public class OrganizationDto implements Serializable {
    private Long id;
    private String name;

    @Override
    public String toString() {
        return id + " " + name;
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
}
