package com.example.magazyn.Controller;

import com.example.magazyn.DTO.DeliveryDTO;
import com.example.magazyn.DTO.ProductDeliveryDTO;
import com.example.magazyn.Entity.Delivery;
import com.example.magazyn.Entity.Product;
import com.example.magazyn.Entity.ProductDelivery;
import com.example.magazyn.Entity.Storage;
import com.example.magazyn.Repository.DeliveryRepository;
import com.example.magazyn.Repository.ProductDeliveryRepository;
import com.example.magazyn.Repository.ProductRepository;
import com.example.magazyn.Repository.StorageRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/delivery")
public class DeliveryController
{
    private final DeliveryRepository repository;
    private final StorageRepository storageRepository;
    private final ProductDeliveryRepository productDeliveryRepository;
    private final ProductRepository productRepository;

    public DeliveryController(DeliveryRepository repository, StorageRepository storageRepository, ProductDeliveryRepository productDeliveryRepository, ProductRepository productRepository)
    {
        this.repository = repository;
        this.storageRepository = storageRepository;
        this.productDeliveryRepository = productDeliveryRepository;
        this.productRepository = productRepository;
    }

    @PostMapping("/")
    public Delivery addDelivery(@RequestBody DeliveryDTO body)
    {
        Storage storage = this.storageRepository.findById(body.storageId())
                .orElseThrow(() -> new IllegalArgumentException("Storage does not exists"));

        Delivery delivery = new Delivery();
        delivery.setStorage(storage);
        delivery.setDate(Date.valueOf(body.date()));

        return this.repository.save(delivery);
    }

    @GetMapping("/{id}")
    public Delivery getDeliveryById(@PathVariable Long id)
    {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Delivery ID"));
    }

    @PostMapping("/{deliveryId}")
    public ProductDelivery addProductDelivery(@PathVariable Long deliveryId, @RequestBody ProductDeliveryDTO body)
    {
        Delivery delivery = this.repository.findById(deliveryId)
                .orElseThrow(() -> new IllegalArgumentException("Delivery not found"));

        Product product = this.productRepository.findById(body.productId())
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        ProductDelivery productDelivery = new ProductDelivery();
        productDelivery.setDelivery(delivery);
        productDelivery.setQuantity(body.quantity());
        productDelivery.setPriceSnapshot(product.getPrice());
        productDelivery.setProduct(product);

        return this.productDeliveryRepository.save(productDelivery);
    }

    @GetMapping("/{id}/products")
    public Delivery getAllProductDelivery(@PathVariable Long id)
    {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid Delivery ID"));
    }

    @GetMapping("/product/{productId}")
    public List<Delivery> getAllDeliveriesWithProductId(@PathVariable Long productId)
    {
        return this.repository.findAllDeliveriesWithProductId(productId);
    }
}
