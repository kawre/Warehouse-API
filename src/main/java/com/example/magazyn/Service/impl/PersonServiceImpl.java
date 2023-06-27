package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.PersonPostDTO;
import com.example.magazyn.Entity.Person;
import com.example.magazyn.Repository.PersonRepository;
import com.example.magazyn.Service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService
{
    private final PersonRepository personRepository;

    @Override
    public Person findById(Long personId)
    {
        return this.personRepository.findById(personId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Person not found"));
    }

    @Override
    public List<Person> findAll()
    {
        return this.personRepository.findAll();
    }

    @Override
    public Optional<Person> findPersonByPesel(String pesel)
    {
        return this.personRepository.findPersonByPesel(pesel);
    }

    @Override
    public Person save(PersonPostDTO dto)
    {
        if (this.findPersonByPesel(dto.pesel()).isPresent())
            throw new ResponseStatusException(CONFLICT, "Person already exists");

        Person person = new Person();
        person.setPesel(dto.pesel());
        person.setName(dto.name());
        person.setSurname(dto.surname());

        try {
            person.setBirthday(Date.valueOf(dto.birthDate()));
        } catch (IllegalArgumentException e) {
            throw new ResponseStatusException(BAD_REQUEST, "Invalid birthdate format");
        }

        return this.personRepository.save(person);
    }
}
