package com.github.ibykhalov.simplespring.model.dto;

import com.github.ibykhalov.simplespring.persistence.Person;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;

@Service
public class PersonMapper {
    private static final ThreadLocal<SimpleDateFormat> dateFormat =
            ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));

    private final ModelMapper modelMapper = new ModelMapper();

    public PersonDto convert(Person entity) {
        PersonDto dto = modelMapper.map(entity, PersonDto.class);
        dto.setBirthDate(dateFormat.get().format(entity.getBirthDate()));
        return dto;
    }
}
