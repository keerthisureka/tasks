package com.example.demo.controller;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Address;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @PostMapping(value = "/createAddress", consumes = "application/json")
    public String createAddress(@RequestBody AddressDto a) {
        return addressService.createAddress(a);
    }

    @GetMapping("/getAllAddresses")
    public List<Address> getAllAddresses() {
        return addressService.getAllAddresses();
    }
}
