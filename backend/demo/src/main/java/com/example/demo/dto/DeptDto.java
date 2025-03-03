package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class DeptDto implements Serializable {
    Long id;
    String deptName;
    String hodName;

    @Override
    public String toString() {
        return this.id + " " + deptName + " " + hodName;
    }
}
