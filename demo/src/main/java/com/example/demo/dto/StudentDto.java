package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class StudentDto implements Serializable {
    Long id;
    String firstName;
    String lastName;
    DeptDto deptDto;
    AddressDto addressDto;

    @Override
    public String toString() {
        return this.id + " " + firstName + " " + lastName;
    }
}
