package com.example.magazyn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "storage")
public class Storage
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String address;
}
