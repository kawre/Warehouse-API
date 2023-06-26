package com.example.magazyn.Controller;

import com.example.magazyn.DTO.CompanyPostDTO;
import com.example.magazyn.DTO.DriverPostDTO;
import com.example.magazyn.Entity.*;
import com.example.magazyn.Repository.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/company")
public class CompanyController
{
    private final CompanyRepository companyRepository;
    private final ConsumerRepository consumerRepository;
    private final SupplierRepository supplierRepository;
    private final DriverRepository driverRepository;
    private final PersonRepository personRepository;

    public CompanyController(CompanyRepository companyRepository, ConsumerRepository consumerRepository, SupplierRepository supplierRepository, DriverRepository driverRepository, PersonRepository personRepository)
    {
        this.companyRepository = companyRepository;
        this.consumerRepository = consumerRepository;
        this.supplierRepository = supplierRepository;
        this.driverRepository = driverRepository;
        this.personRepository = personRepository;
    }

    @PostMapping("/consumers")
    public Consumer addConsumer(@RequestBody CompanyPostDTO body)
    {
        CompanyWithVatinNotExistsOrThrow(body.vatin());

        Company company = new Company();
        company.setName(body.name());
        company.setVatin(body.vatin());
        company.setPhoneNumber(body.phoneNumber());

        Consumer consumer = new Consumer();
        consumer.setCompany(company);

        this.companyRepository.save(company);
        return this.consumerRepository.save(consumer);
    }

    @PostMapping("/suppliers")
    public Supplier addSupplier(@RequestBody CompanyPostDTO body)
    {
        CompanyWithVatinNotExistsOrThrow(body.vatin());

        Company company = new Company();
        company.setName(body.name());
        company.setVatin(body.vatin());
        company.setPhoneNumber(body.phoneNumber());

        Supplier supplier = new Supplier();
        supplier.setCompany(company);

        this.companyRepository.save(company);
        return this.supplierRepository.save(supplier);
    }

    @PostMapping("/suppliers/{supplierId}/drivers")
    public Driver addDriverToSupplier(@RequestBody DriverPostDTO body, @PathVariable Long supplierId)
    {
        Person person = this.personRepository.findById(body.personId())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Person not found"));

        Supplier supplier = this.supplierRepository.findById(supplierId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Supplier not found"));

        Driver driver = new Driver();
        driver.setPerson(person);
        driver.setSupplier(supplier);

        return this.driverRepository.save(driver);
    }

    private void CompanyWithVatinNotExistsOrThrow(String vatin)
    {
        if (this.companyRepository.findByVatin(vatin).isPresent())
            throw new ResponseStatusException(CONFLICT, "Company with this vatin(" + vatin + ") already exists");
    }
}
