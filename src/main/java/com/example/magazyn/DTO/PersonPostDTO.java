package com.example.magazyn.DTO;

public record PersonPostDTO(
        String pesel,
        String name,
        String surname,
        String birthDate
) {}
