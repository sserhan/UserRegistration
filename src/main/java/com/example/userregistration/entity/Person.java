package com.example.userregistration.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(nullable = false)
    private String country;

    private String phone;

    private String gender;

    public Person(String name, LocalDate birthdate, String country, String phone, String gender) {
        this.name = name;
        this.birthdate = birthdate;
        this.country = country;
        this.phone = phone;
        this.gender = gender;
    }

    public Person() {
    }
}
