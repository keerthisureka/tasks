package com.example.demo.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String firstName;
    String lastName;

    @ManyToOne
    @JoinColumn(name = "dept_id")
    private Dept dept;

    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    private Address address;
}
