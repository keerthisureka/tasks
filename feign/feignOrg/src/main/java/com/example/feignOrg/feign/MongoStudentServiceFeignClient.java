package com.example.feignOrg.feign;

import com.example.feignOrg.dto.StudentDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MongoStudentService", url = "http://localhost:8081")
public interface MongoStudentServiceFeignClient {
    @GetMapping("/api/student/getStudentById/{id}")
    StudentDto getStudentById(@PathVariable("id") String id);
}
