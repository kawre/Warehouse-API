package com.example.magazyn.Mappers;

import com.example.magazyn.DTO.PersonDTO;
import com.example.magazyn.Entity.Person;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface PersonMapper
{
    PersonMapper instance = getMapper(PersonMapper.class);

    PersonDTO toDTO(Person person);
}
