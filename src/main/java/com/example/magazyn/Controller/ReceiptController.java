package com.example.magazyn.Controller;

import com.example.magazyn.DTO.ReceiptDTO;
import com.example.magazyn.Mappers.ReceiptMapper;
import com.example.magazyn.Service.impl.ReceiptServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/storage/{storageId}/receipts")
@RequiredArgsConstructor
public class ReceiptController
{
    private final ReceiptServiceImpl receiptService;

    @GetMapping("/")
    public List<ReceiptDTO> getAllReceiptsByStorageId(@PathVariable Long storageId)
    {
        return ReceiptMapper.instance
                .toDtoList(this.receiptService.findReceiptsByStorageId(storageId));
    }

    @GetMapping("/{receiptId}")
    public ReceiptDTO getReceiptById(@PathVariable Long receiptId)
    {
        return ReceiptMapper.instance
                .toDto(this.receiptService.findById(receiptId));
    }
}
