package com.example.magazyn.DTO;

public record ProductDeliveryDTO(
        Long id,
        Integer quantity,
        Float priceSnapshot,
        ProductDTO product
) {}

