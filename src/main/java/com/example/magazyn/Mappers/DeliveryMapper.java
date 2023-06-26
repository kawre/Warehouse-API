package com.example.magazyn.Mappers;

import com.example.magazyn.DTO.DeliveryDTO;
import com.example.magazyn.Entity.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DeliveryMapper
{
    DeliveryMapper instance = Mappers.getMapper(DeliveryMapper.class);

    @Mapping(target = "products", source = "deliveryProducts")
    DeliveryDTO toDTO(Delivery delivery);
}
