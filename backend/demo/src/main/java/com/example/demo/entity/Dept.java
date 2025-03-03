package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String deptName;
    String hodName;

    @OneToMany(mappedBy = "dept", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Student> students;
}
