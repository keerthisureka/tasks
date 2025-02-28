package com.example.mongo.repository;

import com.example.mongo.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee, String>, EmployeeRepositoryCustom {
    List<Employee> findByFirstNameAndLastName(String firstName, String lastName);

    //Page<Employee> findAll(Pageable pageable);
}
