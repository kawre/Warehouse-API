package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.DeliveryPostDTO;
import com.example.magazyn.DTO.ProductDeliveryPostDTO;
import com.example.magazyn.Entity.*;
import com.example.magazyn.Repository.DeliveryRepository;
import com.example.magazyn.Repository.ProductDeliveryRepository;
import com.example.magazyn.Service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService
{
    private final DeliveryRepository deliveryRepository;
    private final ProductDeliveryRepository productDeliveryRepository;
    private final StorageServiceImpl storageService;
    private final DriverServiceImpl driverService;
    private final EmployeeServiceImpl employeeService;
    private final ProductServiceImpl productService;

    @Override
    public List<Delivery> findDeliveriesByStorageId(Long id)
    {
        return this.deliveryRepository.findDeliveriesByStorageId(id);
    }

    @Override
    public Delivery save(DeliveryPostDTO dto, Long storageId)
    {
        Storage storage = this.storageService.findById(storageId);
        Driver driver = this.driverService.findById(dto.driverId());
        Employee employee = this.employeeService.findById(dto.employeeId());

        Delivery delivery = new Delivery();
        delivery.setStorage(storage);
        delivery.setDriver(driver);
        delivery.setEmployee(employee);
        delivery.setDate(Date.valueOf(LocalDate.now()));

        this.deliveryRepository.save(delivery);

        List<ProductDelivery> deliveredProducts = new ArrayList<>();
        for (ProductDeliveryPostDTO productDeliveryDTO : dto.products()) {
            Product product = this.productService.findById(productDeliveryDTO.productId());

            ProductDelivery productDelivery = new ProductDelivery();
            productDelivery.setDelivery(delivery);
            productDelivery.setQuantity(productDeliveryDTO.quantity());
            productDelivery.setPriceSnapshot(product.getPrice());
            productDelivery.setProduct(product);

            deliveredProducts.add(productDelivery);
        }
        this.productDeliveryRepository.saveAll(deliveredProducts);

        delivery.setDeliveryProducts(deliveredProducts);
        return delivery;
    }

    @Override
    public Delivery findById(Long id)
    {
        return this.deliveryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Delivery not found"));
    }

    @Override
    public Delivery findByIdAndStorageId(Long deliveryId, Long storageId)
    {
        Delivery delivery = this.findById(deliveryId);

        if (!delivery.getStorage().getId().equals(storageId))
            throw new ResponseStatusException(BAD_REQUEST, "Delivery was in a different warehouse");

        return delivery;
    }

    @Override
    public List<Delivery> findAll()
    {
        return this.deliveryRepository.findAll();
    }
}
