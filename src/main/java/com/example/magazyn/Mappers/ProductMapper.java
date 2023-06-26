package com.example.magazyn.Mappers;

import com.example.magazyn.DTO.ProductDTO;
import com.example.magazyn.Entity.Product;
import org.mapstruct.Mapper;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductMapper
{
    ProductMapper instance = getMapper(ProductMapper.class);

    ProductDTO toDTO(Product product);
}
