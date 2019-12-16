package com.github.ibykhalov.simplespring;

import com.github.ibykhalov.simplespring.model.dto.PersonDto;
import com.github.ibykhalov.simplespring.persistence.PersonRepository;
import com.github.ibykhalov.simplespring.web.controller.IPersonController;
import com.github.ibykhalov.simplespring.web.controller.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PersonControllerTest {
    @Autowired
    private IPersonController controller;
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    public void findById() {
        Date birthDate = new Date();
        String birthDateString = new SimpleDateFormat("yyyy-MM-dd").format(birthDate);
        long createdId = personService.createPerson("123", "last", "third", birthDate);

        ResponseEntity<PersonDto> founded = controller.findById(createdId);
        assertEquals(200, founded.getStatusCodeValue());
        assertEquals("123", founded.getBody().getFirstName());
        assertEquals("last", founded.getBody().getLastName());
        assertEquals("third", founded.getBody().getThirdName());
        assertEquals(birthDateString, founded.getBody().getBirthDate());

        ResponseEntity<PersonDto> notFounded = controller.findById(0L);
        assertEquals(404, notFounded.getStatusCodeValue());
    }

    @Test
    public void findByBirthDate() throws Exception {
        Date date1 = fromString("2001-12-14 00");
        Date date1withHours = fromString("2001-12-14 01");
        Date date2 = fromString("1999-11-11 00");
        Date date2withHours = fromString("1999-11-11 01");
        Date date2withHours2 = fromString("1999-11-11 02");

        personService.createPerson("1", "1", "1", date1);
        personService.createPerson("1 with hours", "1 with hours", "1 with hours", date1withHours);
        personService.createPerson("2", "2", "2", date2);
        personService.createPerson("2 with hours", "2 with hours", "2 with hours", date2withHours);
        personService.createPerson("2 with hours 2", "2 with hours", "2 with hours2", date2withHours2);

        ResponseEntity<List<PersonDto>> noDateResult = controller.findByBirthDate("1970-01-01");
        assertEquals(0, noDateResult.getBody().size());

        ResponseEntity<List<PersonDto>> date1Result = controller.findByBirthDate("2001-12-14");
        assertEquals(2, date1Result.getBody().size());

        ResponseEntity<List<PersonDto>> date2Result = controller.findByBirthDate("1999-11-11");
        assertEquals(3, date2Result.getBody().size());
    }

    @Test
    public void findByBirthDateFormatError() throws Exception {
        ResponseEntity<List<PersonDto>> errorResponse = controller.findByBirthDate("sdfsdfsd");
        assertEquals(400, errorResponse.getStatusCodeValue());
    }

    private Date fromString(String dateString) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd hh").parse(dateString);
    }
}
