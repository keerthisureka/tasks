package com.example.demo.service;

import com.example.demo.dto.DeptDto;
import com.example.demo.entity.Dept;
import com.example.demo.repository.DeptRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {
    @Autowired
    DeptRepository deptRepository;

    public List<Dept> getAllDepts() {
        return deptRepository.findAll();
    }

    public String createDept(DeptDto d) {
        Dept entityDept = new Dept();
        BeanUtils.copyProperties(d, entityDept);
        deptRepository.save(entityDept);
        return d.toString();
    }
}
