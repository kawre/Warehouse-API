package com.example.magazyn.DTO;

import java.util.List;

public record PurchaseDTO(
        Long employeeId,
        Long storageId,
        Boolean invoice,
        List<PurchaseProductDTO> products
) {}
