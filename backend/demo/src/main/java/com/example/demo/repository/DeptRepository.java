package com.example.demo.repository;

import com.example.demo.entity.Dept;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeptRepository extends JpaRepository<Dept, Long> {
    List<Dept> findAll();
    Optional<Dept> findByDeptName(String deptName);
}
