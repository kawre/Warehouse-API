package com.example.magazyn.Controller;

import com.example.magazyn.DTO.DeliveryDTO;
import com.example.magazyn.DTO.DeliveryPostDTO;
import com.example.magazyn.DTO.ProductDeliveryPostDTO;
import com.example.magazyn.Entity.*;
import com.example.magazyn.Mappers.DeliveryMapper;
import com.example.magazyn.Repository.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/storage/{storageId}/deliveries")
public class DeliveryController
{
    private final DeliveryRepository deliveryRepository;
    private final StorageRepository storageRepository;
    private final ProductDeliveryRepository productDeliveryRepository;
    private final ProductRepository productRepository;
    private final DriverRepository driverRepository;
    private final EmployeeRepository employeeRepository;

    public DeliveryController(DeliveryRepository deliveryRepository, StorageRepository storageRepository, ProductDeliveryRepository productDeliveryRepository, ProductRepository productRepository, DriverRepository driverRepository, EmployeeRepository employeeRepository)
    {
        this.deliveryRepository = deliveryRepository;
        this.storageRepository = storageRepository;
        this.productDeliveryRepository = productDeliveryRepository;
        this.productRepository = productRepository;
        this.driverRepository = driverRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping("/")
    public List<DeliveryDTO> getAllDeliveries(@PathVariable Long storageId)
    {
        return this.deliveryRepository.findAllByStorageId(storageId)
                .stream()
                .map(DeliveryMapper.instance::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/")
    public Delivery addDelivery(@RequestBody DeliveryPostDTO body, @PathVariable Long storageId)
    {
        Storage storage = this.storageRepository.findById(storageId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Storage not found"));

        Driver driver = this.driverRepository.findById(body.driverId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Driver not found"));

        Employee employee = this.employeeRepository.findById(body.employeeId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Employee not found"));

        Delivery delivery = new Delivery();
        delivery.setStorage(storage);
        delivery.setDriver(driver);
        delivery.setEmployee(employee);
        delivery.setDate(Date.valueOf(LocalDate.now()));

        return this.deliveryRepository.save(delivery);
    }

    @GetMapping("/{deliveryId}")
    public DeliveryDTO getDeliveryById(@PathVariable Long deliveryId, @PathVariable Long storageId)
    {
        Delivery delivery = this.deliveryRepository.findByIdAndStorageId(deliveryId, storageId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Delivery not found"));

        return DeliveryMapper.instance.toDTO(delivery);
    }

    @PostMapping("/{deliveryId}")
    public ProductDelivery addProductDelivery(
            @PathVariable Long deliveryId, @RequestBody ProductDeliveryPostDTO body, @PathVariable Long storageId)
    {
        Delivery delivery = this.deliveryRepository.findByIdAndStorageId(deliveryId, storageId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Delivery not found"));

        Product product = this.productRepository.findById(body.productId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Product not in the database"));

        ProductDelivery productDelivery = new ProductDelivery();
        productDelivery.setDelivery(delivery);
        productDelivery.setQuantity(body.quantity());
        productDelivery.setPriceSnapshot(product.getPrice());
        productDelivery.setProduct(product);

        return this.productDeliveryRepository.save(productDelivery);
    }
}
