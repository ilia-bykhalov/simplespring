package com.github.ibykhalov.simplespring;

import com.github.ibykhalov.simplespring.persistence.Person;
import com.github.ibykhalov.simplespring.persistence.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DatabaseInitializationTest {
    @Autowired
    private DatabaseInitialization databaseInitialization;
    @Autowired
    private PersonRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @Test
    public void createTestData() {
        assertEquals(0, repository.count());
        databaseInitialization.createTestData();

        Optional<Person> zeroIdPerson = repository.findById(0L);
        assertTrue(zeroIdPerson.isEmpty());
        assertEquals(11, repository.count());

        long id = repository.findAll().get(0).getId();
        Optional<Person> createdPerson = repository.findById(id);
        assertTrue(createdPerson.isPresent());
    }
}
