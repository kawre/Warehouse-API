package com.example.magazyn.Service;

import com.example.magazyn.DTO.DeliveryPostDTO;
import com.example.magazyn.Entity.Delivery;

import java.util.List;

public interface DeliveryService
{
    List<Delivery> findDeliveriesByStorageId(Long id);

    Delivery save(DeliveryPostDTO dto, Long storageId);

    Delivery findById(Long id);

    Delivery findByIdAndStorageId(Long deliveryId, Long storageId);

    List<Delivery> findAll();
}
