package com.example.userregistration.converter;

import com.example.userregistration.dto.PersonDto;
import com.example.userregistration.entity.Person;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter {
    public PersonDto toDto(Person entity){
        return new PersonDto(entity.getName(), entity.getBirthdate(), entity.getCountry(), entity.getPhone(), entity.getGender());
    }

    public Person toEntity(PersonDto dto){
        return new Person(dto.getName(),dto.getBirthdate(), dto.getCountry(), dto.getPhone(), dto.getGender());
    }
}
