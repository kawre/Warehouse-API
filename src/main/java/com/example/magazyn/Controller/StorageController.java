package com.example.magazyn.Controller;

import com.example.magazyn.DTO.EmployeeDTO;
import com.example.magazyn.DTO.EmployeeFireDTO;
import com.example.magazyn.DTO.EmployeeHireDTO;
import com.example.magazyn.DTO.StoragePostDTO;
import com.example.magazyn.Entity.Delivery;
import com.example.magazyn.Entity.Employee;
import com.example.magazyn.Entity.Person;
import com.example.magazyn.Entity.Storage;
import com.example.magazyn.Mappers.EmployeeMapper;
import com.example.magazyn.Repository.EmployeeRepository;
import com.example.magazyn.Repository.PersonRepository;
import com.example.magazyn.Repository.StorageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController
{
    private final StorageRepository repository;
    private final EmployeeRepository employeeRepository;
    private final PersonRepository personRepository;

    public StorageController(StorageRepository repository, EmployeeRepository employeeRepository, PersonRepository personRepository)
    {
        this.repository = repository;
        this.employeeRepository = employeeRepository;
        this.personRepository = personRepository;
    }

    @PostMapping("/")
    public Storage addStorage(@RequestBody StoragePostDTO body)
    {
        Storage storage = new Storage();
        storage.setAddress(body.address());

        return this.repository.save(storage);
    }

    @GetMapping("/")
    public List<Storage> getAllStorages()
    {
        return this.repository.findAll();
    }

    @GetMapping("/{id}")
    public Storage getStorage(@PathVariable Long id)
    {
        return this.repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Storage not found"));
    }

    @GetMapping("/{storageId}/deliveries")
    public List<Delivery> getAllStorageDeliveries(@PathVariable Long storageId)
    {
        return this.repository.findAllDeliveriesById(storageId);
    }

    @GetMapping("/{storageId}/employees")
    public List<Employee> getAllStorageEmployees(@PathVariable Long storageId)
    {
        Storage storage = this.repository.findById(storageId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Storage not found"));

        return this.employeeRepository.findEmployeesByStorageAndFireDateIsNull(storage);
    }

    @GetMapping("/{storageId}/employees/{employeeId}")
    public EmployeeDTO getStorageEmployee(@PathVariable Long employeeId, @PathVariable Long storageId)
    {
        Employee employee = this.employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Employee not found"));

        if (!employee.getStorage().getId().equals(storageId))
            throw new ResponseStatusException(BAD_REQUEST, "Employee is employed in a different storage");

        return EmployeeMapper.instance.toDTO(employee);
    }

    @PostMapping("/{storageId}/employees/hire")
    public Employee hirePerson(@RequestBody EmployeeHireDTO body, @PathVariable Long storageId)
    {
        Storage storage = this.repository.findById(storageId).orElseThrow(() -> {
            log.error("Storage with ID = " + storageId + " not found");
            return new ResponseStatusException(NOT_FOUND, "Storage not found");
        });

        Person person = this.personRepository.findById(body.personId()).orElseThrow(() -> {
            log.error("Person with ID = " + body.personId() + " not found");
            return new ResponseStatusException(NOT_FOUND, "Person not found");
        });

        if (this.employeeRepository.findByPersonIdAndFireDateIsNull(body.personId()).isPresent()) {
            throw new ResponseStatusException(CONFLICT, "Employee already hired");
        }

        Employee employee = new Employee();
        employee.setPerson(person);
        employee.setStorage(storage);
        employee.setHireDate(Date.valueOf(LocalDate.now()));
        employee.setFireDate(null);

        return this.employeeRepository.save(employee);
    }

    @PostMapping("/{storageId}/employees/fire")
    public Employee fireEmployee(@RequestBody EmployeeFireDTO body, @PathVariable Long storageId)
    {
        if (this.repository.findById(storageId).isEmpty()) {
            log.error("Storage with ID = " + storageId + " not found");
            throw new ResponseStatusException(NOT_FOUND, "Storage not found");
        }

        Employee employee = this.employeeRepository.findById(body.employeeId()).orElseThrow(() -> {
            log.error("Employee with ID = " + body.employeeId() + " not found");
            return new ResponseStatusException(NOT_FOUND, "Employee not found");
        });

        if (employee.getFireDate() != null)
            throw new ResponseStatusException(BAD_REQUEST, "Person is not employed");

        employee.setFireDate(Date.valueOf(LocalDate.now()));
        return this.employeeRepository.save(employee);
    }
}
