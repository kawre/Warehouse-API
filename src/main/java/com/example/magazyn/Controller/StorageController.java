package com.example.magazyn.Controller;

import com.example.magazyn.DTO.StorageDTO;
import com.example.magazyn.Entity.Delivery;
import com.example.magazyn.Entity.Storage;
import com.example.magazyn.Repository.StorageRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage")
public class StorageController
{
    private final StorageRepository repository;

    public StorageController(StorageRepository repository) {this.repository = repository;}

    @PostMapping("/")
    public Storage addStorage(@RequestBody StorageDTO body)
    {
        Storage storage = new Storage();
        storage.setAddress(body.address());

        return this.repository.save(storage);
    }

    @GetMapping("/")
    public List<Storage> getAllStorages()
    {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Storage getStorage(@PathVariable Long id)
    {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Storage not found"));
    }

    @GetMapping("/{id}/deliveries")
    public List<Delivery> getAllStorageDeliveries(@PathVariable Long id)
    {
        return this.repository.findAllDeliveriesById(id);
    }
}
