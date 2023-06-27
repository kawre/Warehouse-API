package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.DriverPostDTO;
import com.example.magazyn.Entity.Driver;
import com.example.magazyn.Entity.Person;
import com.example.magazyn.Entity.Supplier;
import com.example.magazyn.Repository.DriverRepository;
import com.example.magazyn.Service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService
{
    private final DriverRepository driverRepository;
    private final PersonServiceImpl personService;

    @Override
    public Driver findById(Long id)
    {
        return this.driverRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Driver not found"));
    }

    @Override
    public Driver save(DriverPostDTO dto, Supplier supplier)
    {
        Person person = this.personService.findById(dto.personId());

        Driver driver = new Driver();
        driver.setPerson(person);
        driver.setSupplier(supplier);

        return this.driverRepository.save(driver);
    }
}
