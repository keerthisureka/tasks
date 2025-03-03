package com.example.feignOrg.services;

import com.example.feignOrg.dto.ApiResponse;
import com.example.feignOrg.dto.StudentDto;
import com.example.feignOrg.feign.MongoStudentServiceFeignClient;
import com.example.feignOrg.feign.PostgreStudentServiceFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    MongoStudentServiceFeignClient mongoStudentServiceFeignClient;

    @Autowired
    PostgreStudentServiceFeignClient postgreStudentServiceFeignClient;

    public ResponseEntity<ApiResponse<StudentDto>> getStudentDetails(boolean choice, String studentId) {
        if (choice) {
            return mongoStudentServiceFeignClient.getStudentDetails(studentId);
        }
        else {
            return postgreStudentServiceFeignClient.getStudentDetails(studentId);
        }
    }

    public ResponseEntity<ApiResponse<Void>> addStudent(boolean choice, String organizationId, StudentDto studentDto) {
        if (choice) {
            return mongoStudentServiceFeignClient.addStudent(organizationId, studentDto);
        }
        else {
            return postgreStudentServiceFeignClient.addStudent(organizationId, studentDto);
        }
    }
}
