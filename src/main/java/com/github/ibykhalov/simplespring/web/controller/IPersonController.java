package com.github.ibykhalov.simplespring.web.controller;

import com.github.ibykhalov.simplespring.model.dto.PersonDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IPersonController {
    @GetMapping("/findByBirthDate/{birthDate}")
    ResponseEntity<List<PersonDto>> findByBirthDate(@PathVariable("birthDate") String inputString);
    @GetMapping("/{id}")
    ResponseEntity<PersonDto> findById(@PathVariable("id") Long id);
}
