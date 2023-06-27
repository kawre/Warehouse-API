package com.example.magazyn.Mappers;

import com.example.magazyn.DTO.ReceiptDTO;
import com.example.magazyn.Entity.Receipt;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ReceiptMapper
{
    ReceiptMapper instance = getMapper(ReceiptMapper.class);

    @Mapping(source = "receiptProducts", target = "products")
    ReceiptDTO toDto(Receipt receipt);

    List<ReceiptDTO> toDtoList(List<Receipt> receipts);
}
