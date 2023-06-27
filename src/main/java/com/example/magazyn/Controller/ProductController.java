package com.example.magazyn.Controller;

import com.example.magazyn.DTO.ProductDTO;
import com.example.magazyn.DTO.ProductPostDTO;
import com.example.magazyn.Mappers.ProductMapper;
import com.example.magazyn.Service.impl.ProductServiceImpl;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController
{
    private final ProductServiceImpl productService;

    @PostMapping("/")
    public ProductDTO addProduct(@RequestBody ProductPostDTO body)
    {
        return ProductMapper.instance
                .toDto(this.productService.save(body));
    }

    @GetMapping("/")
    public List<ProductDTO> getAllProducts()
    {
        return ProductMapper.instance
                .toDtoList(this.productService.findAll());
    }

    @PostMapping("/{productId}")
    public ProductDTO changeProductPrice(@PathParam("price") Float price, @PathVariable Long productId)
    {
        return ProductMapper.instance
                .toDto(this.productService.changePrice(productId, price));
    }
}
