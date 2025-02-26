package com.example.mongo.repository.impl;

import com.example.mongo.entity.Employee;
import com.example.mongo.repository.EmployeeRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeRepositoryCustomImpl implements EmployeeRepositoryCustom {
    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Employee> getEmployees(String lastName) {
        Query q = new Query();
        q.addCriteria(Criteria.where("lastName").is(lastName));

        return mongoTemplate.find(q, Employee.class);
    }
}
