package com.example.magazyn.Service;

import com.example.magazyn.DTO.StoragePostDTO;
import com.example.magazyn.Entity.Storage;

import java.util.List;
import java.util.Map;

public interface StorageService
{
    Storage save(StoragePostDTO dto);

    Storage findById(Long id);

    List<Storage> findAll();

    Map<String, Integer> calculateWarehouseStock(Long storageId);

    Integer calculateWarehouseProductStock(Long storageId, Long productId);
}
