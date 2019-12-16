package com.github.ibykhalov.simplespring;

import com.github.ibykhalov.simplespring.web.controller.PersonService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class DatabaseInitialization {
    private static final Logger LOGGER = Logger.getLogger(DatabaseInitialization.class.getName());

    private final PersonService personService;

    public DatabaseInitialization(PersonService personService) {
        this.personService = personService;
    }

    @PostConstruct
    public void createTestData() {
        try {
            for (int i = 11; i < 22; i++) {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2012-11-" + i);
                personService.createPerson("first name is " + i, "last name is " + i, "third name is " + i, date);
            }
            LOGGER.info("Test data created");
        } catch (Exception ex) {
            LOGGER.severe("Test data creation failed cause " + ex.getMessage());
        }
    }
}
