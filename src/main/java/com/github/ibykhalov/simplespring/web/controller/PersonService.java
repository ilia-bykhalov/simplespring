package com.github.ibykhalov.simplespring.web.controller;

import com.github.ibykhalov.simplespring.persistence.Person;
import com.github.ibykhalov.simplespring.persistence.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository repository;

    public PersonService(PersonRepository repository) {
        this.repository = repository;
    }

    public List<Person> findByBirthDate(Date birthDate) {
        return repository.findByBirthDate(birthDate);
    }

    public List<Person> queryAll() {
        return repository.findAll();
    }

    public long createPerson(String firstName, String lastName, String thirdName, Date birthDate) {
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setThirdName(thirdName);
        person.setBirthDate(birthDate);
        repository.save(person);
        return person.getId();
    }

    public Optional<Person> findById(Long id) {
        return repository.findById(id);
    }
}
