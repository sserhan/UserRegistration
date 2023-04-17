package com.example.userregistration.service;

import com.example.userregistration.converter.PersonConverter;
import com.example.userregistration.dao.PersonDao;
import com.example.userregistration.dto.PersonDto;
import com.example.userregistration.entity.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
@RequiredArgsConstructor
public class PersonService {

    private final PersonDao personDao;
    private final PersonConverter personConverter;

    public PersonDto savePerson(PersonDto personDto) throws Exception {
        Exception exception = checkPerson(personDto);
        if(exception != null){
            throw exception;
        }
        Person person = personDao.save(personConverter.toEntity(personDto));
        return personConverter.toDto(person);
    }

    public PersonDto getPerson(String name){
        Person person = personDao.findByName(name);
        return person == null ? null : personConverter.toDto(person);
    }

    private Exception checkPerson(PersonDto person){
        if(person.getName() == null){
            return new Exception("Name is null");
        }
        if(person.getBirthdate() == null){
            return new Exception("Birthdate is null");
        }
        if(person.getCountry() == null){
            return new Exception("Country is null");
        }
        if(!"France".equals(person.getCountry())){
            return new Exception("Only French Users are allowed");
        }
        if(LocalDate.now().getYear() - person.getBirthdate().getYear() < 18){
            return new Exception("Only adults are allowed");
        }
        return null;
    }
}
