package com.example.magazyn.Mappers;

import com.example.magazyn.DTO.PersonDTO;
import com.example.magazyn.Entity.Person;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface PersonMapper
{
    PersonMapper instance = getMapper(PersonMapper.class);

    PersonDTO toDto(Person person);

    List<PersonDTO> toDtoList(List<Person> people);
}
