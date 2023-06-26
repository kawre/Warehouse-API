package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceiptRepository extends JpaRepository<Receipt, Long>
{
}
