package com.example.userregistration.controller;

import com.example.userregistration.dto.PersonDto;
import com.example.userregistration.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;

    @PostMapping
    public ResponseEntity savePerson(@RequestBody PersonDto personDto) {
        try {
            PersonDto userSaved = personService.savePerson(personDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User already exists");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @GetMapping("/{name}")
    public ResponseEntity getPerson(@PathVariable("name") String name) {
        try {
            PersonDto user = personService.getPerson(name);
            if(user == null){
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
