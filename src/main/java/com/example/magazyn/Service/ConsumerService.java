package com.example.magazyn.Service;

import com.example.magazyn.DTO.CompanyPostDTO;
import com.example.magazyn.Entity.Consumer;

import java.util.List;

public interface ConsumerService
{
    Consumer save(CompanyPostDTO dto);

    Consumer findById(Long id);

    List<Consumer> findAll();
}
