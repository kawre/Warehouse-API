package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.CompanyPostDTO;
import com.example.magazyn.DTO.DriverPostDTO;
import com.example.magazyn.Entity.Supplier;
import com.example.magazyn.Repository.SupplierRepository;
import com.example.magazyn.Service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService
{
    private final SupplierRepository supplierRepository;
    private final CompanyServiceImpl companyService;
    private final DriverServiceImpl driverService;

    @Override
    public Supplier save(CompanyPostDTO dto)
    {
        if (companyService.companyWithVatinExists(dto.vatin()))
            throw new ResponseStatusException(CONFLICT, "Company with this vatin already exists");

        Supplier supplier = new Supplier();
        supplier.setCompany(this.companyService.save(dto));
        return this.supplierRepository.save(supplier);
    }

    @Override
    public Supplier findById(Long id)
    {
        return this.supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Supplier not found"));
    }

    @Override
    public void addDriver(DriverPostDTO dto, Long supplierId)
    {
        Supplier supplier = this.findById(supplierId);
        this.driverService.save(dto, supplier);
    }
}
