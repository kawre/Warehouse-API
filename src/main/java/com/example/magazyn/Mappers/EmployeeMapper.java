package com.example.magazyn.Mappers;

import com.example.magazyn.DTO.EmployeeDTO;
import com.example.magazyn.Entity.Employee;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface EmployeeMapper
{
    EmployeeMapper instance = getMapper(EmployeeMapper.class);

    EmployeeDTO toDTO(Employee employee);
}
