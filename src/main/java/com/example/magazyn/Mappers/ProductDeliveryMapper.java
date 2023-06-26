package com.example.magazyn.Mappers;

import com.example.magazyn.DTO.ProductDeliveryDTO;
import com.example.magazyn.Entity.ProductDelivery;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductDeliveryMapper
{
    ProductDeliveryMapper instance = getMapper(ProductDeliveryMapper.class);

    ProductDeliveryDTO toDTO(ProductDelivery productDelivery);
}
