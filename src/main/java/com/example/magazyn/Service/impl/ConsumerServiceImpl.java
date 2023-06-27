package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.CompanyPostDTO;
import com.example.magazyn.Entity.Consumer;
import com.example.magazyn.Repository.ConsumerRepository;
import com.example.magazyn.Service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ConsumerServiceImpl implements ConsumerService
{
    private final ConsumerRepository consumerRepository;
    private final CompanyServiceImpl companyService;

    @Override
    public Consumer save(CompanyPostDTO dto)
    {
        if (companyService.companyWithVatinExists(dto.vatin()))
            throw new ResponseStatusException(CONFLICT, "Company with this vatin already exists");

        Consumer consumer = new Consumer();
        consumer.setCompany(this.companyService.save(dto));
        return this.consumerRepository.save(consumer);
    }

    @Override
    public Consumer findById(Long id)
    {
        return this.consumerRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Consumer not found"));
    }

    @Override
    public List<Consumer> findAll()
    {
        return this.consumerRepository.findAll();
    }
}
