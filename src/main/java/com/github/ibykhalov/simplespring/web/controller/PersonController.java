package com.github.ibykhalov.simplespring.web.controller;

import com.github.ibykhalov.simplespring.model.dto.PersonDto;
import com.github.ibykhalov.simplespring.model.dto.PersonMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/person")
public class PersonController implements IPersonController {
    private final PersonService service;
    private final PersonMapper personMapper;

    public PersonController(PersonService service, PersonMapper personMapper) {
        this.service = service;
        this.personMapper = personMapper;
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<PersonDto> findById(@PathVariable("id") Long id) {
        return service.findById(id)
                .map(personMapper::convert)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    @GetMapping("/findByBirthDate/{birthDate}")
    public ResponseEntity<List<PersonDto>> findByBirthDate(@PathVariable("birthDate") String inputString) {
        try {
            Date birthDate = new SimpleDateFormat("yyyy-MM-dd").parse(inputString);
            List<PersonDto> resultPersons = service.findByBirthDate(birthDate)
                    .stream()
                    .map(personMapper::convert)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(resultPersons);
        } catch (ParseException ex) {
            return ResponseEntity.badRequest().build();
        }
    }
}
