package com.example.userregistration.controller;

import com.example.userregistration.dto.PersonDto;
import com.example.userregistration.service.PersonService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

@WebMvcTest(value = PersonController.class)
public class PersonControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    PersonDto mockPerson = new PersonDto("TEST", LocalDate.of(1990,1,1),"France","0655443322","M");

    String personJson  = "{\"name\":\"TEST\",\"birthdate\":\"1990-01-01\",\"country\":\"France\",\"phone\":\"0655443322\",\"gender\":\"M\"}";

    @Test
    public void getPerson() throws Exception {
        Mockito.when(personService.getPerson(Mockito.anyString())).thenReturn(mockPerson);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/person/TEST")).andReturn();
        Assertions.assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
        Assertions.assertEquals(personJson, result.getResponse().getContentAsString());
    }

    @Test
    public void getPerson_NoContent() throws Exception {
        Mockito.when(personService.getPerson(Mockito.anyString())).thenReturn(null);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/person/TEST")).andReturn();
        Assertions.assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
    }

    @Test
    public void savePerson() throws Exception {
        Mockito.when(personService.savePerson(Mockito.any(PersonDto.class))).thenReturn(mockPerson);
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/person").accept(MediaType.APPLICATION_JSON)
                .content(personJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assertions.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getStatus());
        Assertions.assertEquals(personJson, result.getResponse().getContentAsString());
    }

    @Test
    public void savePerson_DataIntegrityException() throws  Exception {
        Mockito.when(personService.savePerson(Mockito.any(PersonDto.class))).thenThrow(new DataIntegrityViolationException("exception"));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/person").accept(MediaType.APPLICATION_JSON)
                .content(personJson).contentType(MediaType.APPLICATION_JSON)).andReturn();
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), result.getResponse().getStatus());
        Assertions.assertEquals("User already exists", result.getResponse().getContentAsString());
    }

}
