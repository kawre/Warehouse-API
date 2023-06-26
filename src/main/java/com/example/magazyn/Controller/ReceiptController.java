package com.example.magazyn.Controller;

import com.example.magazyn.Entity.Consumer;
import com.example.magazyn.Entity.Receipt;
import com.example.magazyn.Repository.ConsumerRepository;
import com.example.magazyn.Repository.ReceiptRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/receipt")
public class ReceiptController
{
    private final ReceiptRepository repository;
    private final ConsumerRepository consumerRepository;

    public ReceiptController(ReceiptRepository repository, ConsumerRepository consumerRepository)
    {
        this.repository = repository;
        this.consumerRepository = consumerRepository;
    }

    @GetMapping("/{receiptId}")
    public Receipt getReceiptById(@PathVariable Long receiptId)
    {
        return this.repository.findById(receiptId).orElseThrow(() -> new IllegalArgumentException("Receipt not found"));
    }

    @GetMapping("/consumer/{consumerId}")
    public List<Receipt> getConsumerReceipts(@PathVariable Long consumerId)
    {
        Consumer consumer = this.consumerRepository.findById(consumerId)
                .orElseThrow(() -> new IllegalArgumentException("Consumer not found"));

        return consumer.getReceipts();
    }
}
