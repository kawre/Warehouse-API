package com.example.magazyn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "person")
public class Person
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String pesel;

    private String name;

    private String surname;

    private Date birthday;
}
