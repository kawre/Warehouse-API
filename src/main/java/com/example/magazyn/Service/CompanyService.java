package com.example.magazyn.Service;

import com.example.magazyn.DTO.CompanyPostDTO;
import com.example.magazyn.Entity.Company;

public interface CompanyService
{
    boolean companyWithVatinExists(String vatin);

    Company save(CompanyPostDTO dto);
}
