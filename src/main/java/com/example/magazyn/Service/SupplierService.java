package com.example.magazyn.Service;

import com.example.magazyn.DTO.CompanyPostDTO;
import com.example.magazyn.DTO.DriverPostDTO;
import com.example.magazyn.Entity.Supplier;

public interface SupplierService
{
    Supplier findById(Long id);

    Supplier save(CompanyPostDTO dto);

    void addDriver(DriverPostDTO dto, Long supplierId);
}
