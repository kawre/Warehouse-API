package com.example.magazyn.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company")
public class Company
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String phoneNumber;

    private String name;

    private String vatin;

    @OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
    private Consumer consumer;

    @OneToOne(mappedBy = "company", fetch = FetchType.LAZY)
    private Supplier supplier;
}
