package com.example.magazyn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "invoice")
public class Invoice
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
