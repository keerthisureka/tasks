package com.example.feignOrg.services;

import com.example.feignOrg.dto.StudentDto;
import com.example.feignOrg.feign.MongoStudentServiceFeignClient;
import com.example.feignOrg.feign.PostgreStudentServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    MongoStudentServiceFeignClient mongoStudentServiceFeignClient;

    @Autowired
    PostgreStudentServiceFeignClient postgreStudentServiceFeignClient;

    public StudentDto toCallMongo(boolean choice, String id) {
        if (choice) {
            return mongoStudentServiceFeignClient.getStudentById(id);
        }
        else {
            return postgreStudentServiceFeignClient.getStudentById(id);
        }
    }
}
