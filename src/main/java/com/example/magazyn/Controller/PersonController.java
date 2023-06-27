package com.example.magazyn.Controller;

import com.example.magazyn.DTO.PersonDTO;
import com.example.magazyn.DTO.PersonPostDTO;
import com.example.magazyn.Mappers.PersonMapper;
import com.example.magazyn.Service.impl.PersonServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
@Slf4j
public class PersonController
{
    private final PersonServiceImpl personService;

    @GetMapping("/")
    public List<PersonDTO> getAllPeople()
    {
        return PersonMapper.instance
                .toDtoList(this.personService.findAll());
    }

    @PostMapping("/")
    public PersonDTO addPerson(@RequestBody PersonPostDTO body)
    {
        return PersonMapper.instance
                .toDto(this.personService.save(body));
    }

    @GetMapping("/{personId}")
    public PersonDTO getPersonById(@PathVariable Long personId)
    {
        return PersonMapper.instance
                .toDto(this.personService.findById(personId));
    }
}
