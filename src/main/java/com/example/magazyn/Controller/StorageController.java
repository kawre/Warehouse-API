package com.example.magazyn.Controller;

import com.example.magazyn.DTO.EmployeeDTO;
import com.example.magazyn.DTO.StoragePostDTO;
import com.example.magazyn.Entity.Employee;
import com.example.magazyn.Entity.Storage;
import com.example.magazyn.Service.impl.EmployeeServiceImpl;
import com.example.magazyn.Service.impl.StorageServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/storage")
@RequiredArgsConstructor
@Slf4j
public class StorageController
{
    private final EmployeeServiceImpl employeeService;
    private final StorageServiceImpl storageService;

    @PostMapping("/")
    public Storage addStorage(@RequestBody StoragePostDTO body)
    {
        return this.storageService.save(body);
    }

    @GetMapping("/")
    public List<Storage> getAllStorages()
    {
        return this.storageService.findAll();
    }

    @GetMapping("/{id}")
    public Storage getStorage(@PathVariable Long id)
    {
        return this.storageService.findById(id);
    }

    @GetMapping("/{storageId}/employees")
    public List<Employee> getAllStorageEmployees(@PathVariable Long storageId)
    {
        return this.employeeService.findEmployeesByStorageIdAndFireDateIsNull(storageId);
    }

    @GetMapping("/{storageId}/employees/{employeeId}")
    public EmployeeDTO getStorageEmployee(@PathVariable Long employeeId, @PathVariable Long storageId)
    {
        return this.employeeService.findByIdAndStorageId(employeeId, storageId);
    }

    @PostMapping("/{storageId}/employees/hire/{personId}")
    public Employee hirePerson(@PathVariable Long storageId, @PathVariable Long personId)
    {
        return this.employeeService.hire(personId, storageId);
    }

    @PostMapping("/{storageId}/employees/{employeeId}/fire")
    public Employee fireEmployee(@PathVariable Long storageId, @PathVariable Long employeeId)
    {
        return this.employeeService.fire(employeeId, storageId);
    }

    @GetMapping("/{storageId}/stock/")
    public Map<String, Integer> getStorageStock(@PathVariable Long storageId)
    {
        return this.storageService.calculateWarehouseStock(storageId);
    }

    @GetMapping("/{storageId}/stock/{productId}")
    public Integer getStorageProductStock(@PathVariable Long storageId, @PathVariable Long productId)
    {
        return this.storageService.calculateWarehouseProductStock(storageId, productId);
    }
}
