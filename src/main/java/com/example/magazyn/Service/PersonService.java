package com.example.magazyn.Service;

import com.example.magazyn.DTO.PersonPostDTO;
import com.example.magazyn.Entity.Person;

import java.util.List;
import java.util.Optional;

public interface PersonService
{
    Person findById(Long personId);

    Optional<Person> findPersonByPesel(String pesel);

    Person save(PersonPostDTO dto);

    List<Person> findAll();
}
