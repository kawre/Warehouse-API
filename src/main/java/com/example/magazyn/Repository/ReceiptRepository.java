package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>
{
    List<Receipt> findReceiptsByConsumerId(Long consumerId);

    List<Receipt> findReceiptsByStorageId(Long storageId);
}
