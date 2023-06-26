package com.example.magazyn.Controller;

import com.example.magazyn.DTO.ProductPostDTO;
import com.example.magazyn.Entity.Product;
import com.example.magazyn.Repository.ProductRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/product")
public class ProductController
{
    private final ProductRepository repository;

    public ProductController(ProductRepository repository)
    {
        this.repository = repository;
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody ProductPostDTO body)
    {
        Product product = new Product();
        product.setName(body.name());
        product.setPrice(body.price());

        return this.repository.save(product);
    }

    @GetMapping("/")
    public List<Product> getAllProducts()
    {
        return this.repository.findAll();
    }

    @PostMapping("/{productId}")
    public Product changeProductPrice(@PathParam("price") Float price, @PathVariable Long productId)
    {
        Product product = this.repository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not found"));

        product.setPrice(price);
        return this.repository.save(product);
    }
}
