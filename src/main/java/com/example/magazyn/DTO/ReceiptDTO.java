package com.example.magazyn.DTO;

import com.example.magazyn.Entity.Invoice;

import java.sql.Date;
import java.util.List;

public record ReceiptDTO(
        Long id,
        Date date,
        List<ReceiptProductDTO> products,
        Invoice invoice
) {}
