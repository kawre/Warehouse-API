package com.example.magazyn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    private Driver driver;

    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    private Employee employee;
}
