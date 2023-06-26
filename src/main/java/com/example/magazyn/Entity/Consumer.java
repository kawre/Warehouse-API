package com.example.magazyn.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "consumer")
public class Consumer
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "consumer", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Receipt> receipts;
}
