package com.example.magazyn.Mappers;

import com.example.magazyn.DTO.CompanyDTO;
import com.example.magazyn.DTO.ConsumerDTO;
import com.example.magazyn.DTO.SupplierDTO;
import com.example.magazyn.Entity.Company;
import com.example.magazyn.Entity.Consumer;
import com.example.magazyn.Entity.Supplier;
import org.mapstruct.Mapper;

import java.util.List;

import static org.mapstruct.factory.Mappers.getMapper;

@Mapper
public interface CompanyMapper
{
    CompanyMapper instance = getMapper(CompanyMapper.class);

    CompanyDTO companyToCompanyDto(Company company);

    ConsumerDTO consumerToConsumerDto(Consumer consumer);

    List<ConsumerDTO> consumerToConsumerDtoList(List<Consumer> consumers);

    SupplierDTO supplierToSupplierDto(Supplier supplier);
}
