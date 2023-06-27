package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.ProductPostDTO;
import com.example.magazyn.Entity.Product;
import com.example.magazyn.Repository.ProductRepository;
import com.example.magazyn.Service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService
{
    private final ProductRepository productRepository;

    @Override
    public Product findById(Long id)
    {
        return this.productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found"));
    }

    @Override
    public List<Product> findAll()
    {
        return this.productRepository.findAll();
    }

    @Override
    public Product save(ProductPostDTO dto)
    {
        Product product = new Product();
        product.setName(dto.name());
        product.setPrice(dto.price());

        return this.productRepository.save(product);
    }

    @Override
    public Product changePrice(Long productId, Float newPrice)
    {
        Product product = this.findById(productId);

        product.setPrice(newPrice);
        return this.productRepository.save(product);
    }
}
