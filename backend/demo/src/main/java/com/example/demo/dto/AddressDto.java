package com.example.demo.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddressDto implements Serializable {
    Long id;
    String city;
    String country;

    @Override
    public String toString() {
        return this.id + " " + city + " " + country;
    }
}
