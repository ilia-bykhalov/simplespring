package com.github.ibykhalov.simplespring.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(
            value = "select * from person where ?1 = trunc(birth_date)",
            countQuery = "select count(*) from person where ?1 = trunc(birth_date)",
            nativeQuery = true)
    List<Person> findByBirthDate(Date birthDate);
}
