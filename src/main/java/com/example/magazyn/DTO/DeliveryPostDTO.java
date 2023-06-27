package com.example.magazyn.DTO;

import java.util.List;

public record DeliveryPostDTO(
        Long driverId,
        Long employeeId,
        List<ProductDeliveryPostDTO> products
) {}
