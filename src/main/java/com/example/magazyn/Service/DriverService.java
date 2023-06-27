package com.example.magazyn.Service;

import com.example.magazyn.DTO.DriverPostDTO;
import com.example.magazyn.Entity.Driver;
import com.example.magazyn.Entity.Supplier;

public interface DriverService
{
    Driver findById(Long id);

    Driver save(DriverPostDTO dto, Supplier supplier);
}
