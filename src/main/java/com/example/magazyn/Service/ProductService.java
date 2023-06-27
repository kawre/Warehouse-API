package com.example.magazyn.Service;

import com.example.magazyn.DTO.ProductPostDTO;
import com.example.magazyn.Entity.Product;

import java.util.List;

public interface ProductService
{
    Product findById(Long id);

    List<Product> findAll();

    Product save(ProductPostDTO dto);

    Product changePrice(Long productId, Float newPrice);
}
