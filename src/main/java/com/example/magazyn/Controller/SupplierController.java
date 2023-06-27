package com.example.magazyn.Controller;

import com.example.magazyn.DTO.CompanyPostDTO;
import com.example.magazyn.DTO.DriverPostDTO;
import com.example.magazyn.DTO.SupplierDTO;
import com.example.magazyn.Mappers.CompanyMapper;
import com.example.magazyn.Service.impl.SupplierServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/suppliers")
@RequiredArgsConstructor
public class SupplierController
{
    private final SupplierServiceImpl supplierService;

    @PostMapping("/")
    public SupplierDTO addSupplier(@RequestBody CompanyPostDTO body)
    {
        return CompanyMapper.instance
                .supplierToSupplierDto(this.supplierService.save(body));
    }

    @PostMapping("/{supplierId}/drivers/")
    public String addDriverToSupplier(@RequestBody DriverPostDTO body, @PathVariable Long supplierId)
    {
        this.supplierService.addDriver(body, supplierId);
        return "Driver succesfully added";
    }
}
