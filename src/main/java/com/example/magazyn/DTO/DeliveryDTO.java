package com.example.magazyn.DTO;

import java.sql.Date;
import java.util.List;

public record DeliveryDTO(
        Long id,
        Date date,
        List<ProductDeliveryDTO> products
) {}
