package com.github.ibykhalov.simplespring;

import com.github.ibykhalov.simplespring.persistence.Person;
import com.github.ibykhalov.simplespring.persistence.PersonRepository;
import com.github.ibykhalov.simplespring.web.controller.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class PersonServiceTest {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    public void createPerson() {
        Date birthDate = new Date();
        long personId = personService.createPerson("1", "2", "3", birthDate);

        Person created = personService.findById(personId).get();

        assertEquals("1", created.getFirstName());
        assertEquals("2", created.getLastName());
        assertEquals("3", created.getThirdName());
        assertEquals(birthDate, created.getBirthDate());
    }

    @Test
    public void createPersonNullConstraints() {
        Date birthDate = new Date();

        assertThrows(Exception.class, () -> personService.createPerson("1", "2", "3", null));
        assertThrows(Exception.class, () -> personService.createPerson(null, "2", "3", birthDate));
        assertThrows(Exception.class, () -> personService.createPerson("1", null, "3", birthDate));
        assertThrows(Exception.class, () -> personService.createPerson("1", "2", null, birthDate));
    }
}
