package com.example.magazyn.Controller;

import com.example.magazyn.DTO.DeliveryDTO;
import com.example.magazyn.DTO.DeliveryPostDTO;
import com.example.magazyn.Mappers.DeliveryMapper;
import com.example.magazyn.Service.impl.DeliveryServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/storage/{storageId}/deliveries")
@RequiredArgsConstructor
public class DeliveryController
{
    private final DeliveryServiceImpl deliveryService;

    @GetMapping("/")
    public List<DeliveryDTO> getAllDeliveriesByStorageId(@PathVariable Long storageId)
    {
        return DeliveryMapper.instance
                .toDtoList(this.deliveryService.findDeliveriesByStorageId(storageId));
    }

    @PostMapping("/")
    public DeliveryDTO addDelivery(@RequestBody DeliveryPostDTO body, @PathVariable Long storageId)
    {
        return DeliveryMapper.instance
                .toDto(this.deliveryService.save(body, storageId));
    }

    @GetMapping("/{deliveryId}")
    public DeliveryDTO getDeliveryById(@PathVariable Long deliveryId, @PathVariable Long storageId)
    {
        return DeliveryMapper.instance
                .toDto(this.deliveryService.findByIdAndStorageId(deliveryId, storageId));
    }
}
