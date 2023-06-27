package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.CompanyPostDTO;
import com.example.magazyn.Entity.Company;
import com.example.magazyn.Repository.CompanyRepository;
import com.example.magazyn.Service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService
{
    private final CompanyRepository companyRepository;

    @Override
    public Company save(CompanyPostDTO dto)
    {
        Company company = new Company();
        company.setName(dto.name());
        company.setVatin(dto.vatin());
        company.setPhoneNumber(dto.phoneNumber());

        return this.companyRepository.save(company);
    }

    @Override
    public boolean companyWithVatinExists(String vatin)
    {
        return this.companyRepository.findByVatin(vatin).isPresent();
    }
}
