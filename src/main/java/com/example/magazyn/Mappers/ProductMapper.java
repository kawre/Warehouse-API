package com.example.magazyn.Mappers;

import com.example.magazyn.DTO.ProductDTO;
import com.example.magazyn.Entity.Product;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface ProductMapper
{
    ProductMapper instance = getMapper(ProductMapper.class);

    ProductDTO toDto(Product product);

    List<ProductDTO> toDtoList(List<Product> all);
}
