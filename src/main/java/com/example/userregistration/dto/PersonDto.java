package com.example.userregistration.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PersonDto {
    private String name;
    private LocalDate birthdate;
    private String country;
    private String phone;
    private String gender;

    public PersonDto(String name, LocalDate birthdate, String country, String phone, String gender) {
        this.name = name;
        this.birthdate = birthdate;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }

    public PersonDto() {
    }
}
