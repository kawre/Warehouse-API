package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long>
{
}
