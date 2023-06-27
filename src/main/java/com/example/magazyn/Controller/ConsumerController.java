package com.example.magazyn.Controller;

import com.example.magazyn.DTO.CompanyPostDTO;
import com.example.magazyn.DTO.ConsumerDTO;
import com.example.magazyn.DTO.PurchaseDTO;
import com.example.magazyn.DTO.ReceiptDTO;
import com.example.magazyn.Mappers.CompanyMapper;
import com.example.magazyn.Mappers.ReceiptMapper;
import com.example.magazyn.Service.impl.ConsumerServiceImpl;
import com.example.magazyn.Service.impl.ReceiptServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consumers")
@RequiredArgsConstructor
public class ConsumerController
{
    private final ConsumerServiceImpl consumerService;
    private final ReceiptServiceImpl receiptService;

    @GetMapping("/")
    public List<ConsumerDTO> getAllConsumers()
    {
        return CompanyMapper.instance
                .consumerToConsumerDtoList(this.consumerService.findAll());
    }

    @PostMapping("/")
    public ConsumerDTO addConsumer(@RequestBody CompanyPostDTO body)
    {
        return CompanyMapper.instance
                .consumerToConsumerDto(this.consumerService.save(body));
    }

    @PostMapping("/{consumerId}/purchases/")
    public ReceiptDTO makeConsumerPurchase(@PathVariable Long consumerId, @RequestBody PurchaseDTO body)
    {
        return ReceiptMapper.instance
                .toDto(this.receiptService.makePurchase(body, consumerId));
    }

    @GetMapping("/{consumerId}/purchases/")
    public List<ReceiptDTO> getAllConsumerReceipts(@PathVariable Long consumerId)
    {
        return ReceiptMapper.instance
                .toDtoList(this.receiptService.findReceiptsByConsumerId(consumerId));
    }
}
