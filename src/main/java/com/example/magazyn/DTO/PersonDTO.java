package com.example.magazyn.DTO;

import java.sql.Date;

public record PersonDTO(
        Long id,
        String pesel,
        String name,
        String surname,
        Date birthday
) {}
