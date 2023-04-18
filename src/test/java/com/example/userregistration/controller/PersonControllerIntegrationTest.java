package com.example.userregistration.controller;

import com.example.userregistration.UserRegistrationApplication;
import com.example.userregistration.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDate;

@SpringBootTest(classes = UserRegistrationApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void integrationTest() {

        Person person = new Person("TEST", LocalDate.of(1990,1,1),"France","0655443322","M");

        ResponseEntity<String> responseGet = restTemplate.postForEntity("http://localhost:" + port + "/person", person, String.class);

        Assertions.assertEquals(HttpStatus.CREATED,responseGet.getStatusCode());

        ResponseEntity<String> responsePost = restTemplate.getForEntity("http://localhost:" + port + "/person/TEST", String.class);

        Assertions.assertEquals(HttpStatus.OK, responsePost.getStatusCode());

        String expected = "{\"name\":\"TEST\",\"birthdate\":\"1990-01-01\",\"country\":\"France\",\"phone\":\"0655443322\",\"gender\":\"M\"}";

        Assertions.assertEquals(expected, responsePost.getBody());
    }
}