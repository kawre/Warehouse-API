package com.example.magazyn.Controller;

import com.example.magazyn.DTO.ProductDTO;
import com.example.magazyn.Entity.Product;
import com.example.magazyn.Repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public Product addProduct(@RequestBody ProductDTO body)
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

    @GetMapping("/consumer/{consumerId}")
    public List<Product> getAllProductsBoughtByConsumer(@PathVariable Long consumerId)
    {
        List<Product> products = this.repository.getAllProductsBoughtByCustomer(consumerId);
        System.out.println(products);
        return products;
    }
}
