package com.example.magazyn.DTO;

import java.io.Serializable;
import java.sql.Date;

public record EmployeeDTO(
        Long id,
        PersonDTO person,
        Date hireDate,
        Date fireDate
) implements Serializable {}
