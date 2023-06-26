package com.example.magazyn.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "product_delivery")
public class ProductDelivery
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Float priceSnapshot;

    private Integer quantity;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
}
