package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.StoragePostDTO;
import com.example.magazyn.Entity.*;
import com.example.magazyn.Repository.StorageRepository;
import com.example.magazyn.Service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class StorageServiceImpl implements StorageService
{
    private final StorageRepository storageRepository;
    private final ProductServiceImpl productService;
    @Lazy private final DeliveryServiceImpl deliveryService;
    @Lazy private final ReceiptServiceImpl receiptService;

    @Override
    public Storage findById(Long id)
    {
        return storageRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Storage not found"));
    }

    @Override
    public List<Storage> findAll()
    {
        return storageRepository.findAll();
    }

    @Override
    public Storage save(StoragePostDTO dto)
    {
        Storage storage = new Storage();
        storage.setAddress(dto.address());

        return this.storageRepository.save(storage);
    }

    @Override
    public Map<String, Integer> calculateWarehouseStock(Long storageId)
    {
        List<Delivery> deliveries = this.deliveryService.findDeliveriesByStorageId(storageId);
        List<Receipt> receipts = this.receiptService.findReceiptsByStorageId(storageId);

        Map<String, Integer> stock = new HashMap<>();

        for (Delivery delivery : deliveries)
            for (ProductDelivery productDelivery : delivery.getDeliveryProducts())
                stock.merge(productDelivery.getProduct().getName(), productDelivery.getQuantity(), Integer::sum);

        for (Receipt receipt : receipts)
            for (ReceiptProduct receiptProduct : receipt.getReceiptProducts())
                stock.merge(receiptProduct.getProduct().getName(), -receiptProduct.getQuantity(), Integer::sum);

        return stock;
    }

    @Override
    public Integer calculateWarehouseProductStock(Long storageId, Long productId)
    {
        Product product = this.productService.findById(productId);
        return this.calculateWarehouseStock(storageId).get(product.getName());
    }
}
