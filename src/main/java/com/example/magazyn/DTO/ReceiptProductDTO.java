package com.example.magazyn.DTO;

public record ReceiptProductDTO(
        Long id,
        Integer quantity,
        ProductDTO product
) {}
