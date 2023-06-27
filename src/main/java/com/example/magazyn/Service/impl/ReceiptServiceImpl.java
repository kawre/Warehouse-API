package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.PurchaseDTO;
import com.example.magazyn.DTO.PurchaseProductDTO;
import com.example.magazyn.Entity.*;
import com.example.magazyn.Repository.InvoiceRepository;
import com.example.magazyn.Repository.ReceiptProductRepository;
import com.example.magazyn.Repository.ReceiptRepository;
import com.example.magazyn.Service.ReceiptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ReceiptServiceImpl implements ReceiptService
{
    private final ReceiptRepository receiptRepository;
    private final ReceiptProductRepository receiptProductRepository;
    private final StorageServiceImpl storageService;
    private final ConsumerServiceImpl consumerService;
    private final EmployeeServiceImpl employeeService;
    private final ProductServiceImpl productService;
    private final InvoiceRepository invoiceRepository;

    @Override
    public List<Receipt> findReceiptsByStorageId(Long storageId)
    {
        return this.receiptRepository.findReceiptsByStorageId(storageId);
    }

    @Override
    public Receipt findById(Long id)
    {
        return this.receiptRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Receipt not found"));
    }

    @Override
    public List<Receipt> findReceiptsByConsumerId(Long consumerId)
    {
        return this.receiptRepository.findReceiptsByConsumerId(consumerId);
    }

    @Override
    public Receipt makePurchase(PurchaseDTO dto, Long consumerId)
    {
        Storage storage = this.storageService.findById(dto.storageId());
        Consumer consumer = this.consumerService.findById(consumerId);
        Employee employee = this.employeeService.findById(dto.employeeId());

        Receipt receipt = new Receipt();
        receipt.setEmployee(employee);
        receipt.setStorage(storage);
        receipt.setConsumer(consumer);
        receipt.setDate(Date.valueOf(LocalDate.now()));

        if (dto.invoice())
            receipt.setInvoice(this.invoiceRepository.save(new Invoice()));

        this.receiptRepository.save(receipt);

        List<ReceiptProduct> products = new ArrayList<>();
        for (PurchaseProductDTO purchaseProduct : dto.products()) {
            System.out.println(purchaseProduct.quantity());
            ReceiptProduct receiptProduct = new ReceiptProduct();

            Product product = this.productService.findById(purchaseProduct.productId());

            receiptProduct.setProduct(product);
            receiptProduct.setReceipt(receipt);
            receiptProduct.setQuantity(purchaseProduct.quantity());

            products.add(receiptProduct);
        }
        this.receiptProductRepository.saveAll(products);

        receipt.setReceiptProducts(products);
        return receipt;
    }
}
