package com.example.magazyn.Repository;

import com.example.magazyn.Entity.ReceiptProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptProductRepository extends JpaRepository<ReceiptProduct, Long>
{
}
