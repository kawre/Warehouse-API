package com.example.magazyn.Service;

import com.example.magazyn.DTO.PurchaseDTO;
import com.example.magazyn.Entity.Receipt;

import java.util.List;

public interface ReceiptService
{
    List<Receipt> findReceiptsByStorageId(Long storageId);

    Receipt findById(Long id);

    List<Receipt> findReceiptsByConsumerId(Long consumerId);

    Receipt makePurchase(PurchaseDTO dto, Long consumerId);
}
