package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long>
{
}
