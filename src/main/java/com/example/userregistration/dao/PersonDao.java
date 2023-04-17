package com.example.userregistration.dao;

import com.example.userregistration.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDao extends JpaRepository<Person, Long> {
    Person findByName(String name);
}
