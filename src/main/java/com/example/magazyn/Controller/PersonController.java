package com.example.magazyn.Controller;

import com.example.magazyn.DTO.PersonPostDTO;
import com.example.magazyn.Entity.Person;
import com.example.magazyn.Repository.DriverRepository;
import com.example.magazyn.Repository.EmployeeRepository;
import com.example.magazyn.Repository.PersonRepository;
import com.example.magazyn.Repository.StorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController
{
    private final PersonRepository personRepository;
    private final EmployeeRepository employeeRepository;
    private final DriverRepository driverRepository;
    private final StorageRepository storageRepository;

    public PersonController(PersonRepository personRepository, EmployeeRepository employeeRepository, DriverRepository driverRepository, StorageRepository storageRepository)
    {
        this.personRepository = personRepository;
        this.employeeRepository = employeeRepository;
        this.driverRepository = driverRepository;
        this.storageRepository = storageRepository;
    }

    @GetMapping("/")
    public List<Person> getAllPeople()
    {
        return this.personRepository.findAll();
    }

    @PostMapping("/")
    public Person addPerson(@RequestBody PersonPostDTO body)
    {
        if (this.personRepository.findPersonByPesel(body.pesel()).isPresent())
            throw new ResponseStatusException(CONFLICT, "Person already exists");

        Person person = new Person();
        person.setPesel(body.pesel());
        person.setName(body.name());
        person.setSurname(body.surname());

        try {
            person.setBirthday(Date.valueOf(body.birthDate()));
        } catch (IllegalArgumentException e) {
            log.error("Invalid date format: " + e.getMessage());
            throw new ResponseStatusException(BAD_REQUEST, "Invalid birthdate format");
        }

        return this.personRepository.save(person);
    }

    @GetMapping("/{personId}")
    public Person getPersonById(@PathVariable Long personId)
    {
        return this.personRepository.findById(personId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Person not found"));
    }
}
