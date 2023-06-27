package com.example.magazyn.Service;

import com.example.magazyn.DTO.EmployeeDTO;
import com.example.magazyn.Entity.Employee;

import java.util.List;

public interface EmployeeService
{
    Employee findById(Long id);

    boolean isEmployed(Long personId);

    EmployeeDTO findByIdAndStorageId(Long employeeId, Long storageId);

    List<Employee> findEmployeesByStorageIdAndFireDateIsNull(Long storageId);

    Employee hire(Long personId, Long storageId);

    Employee fire(Long employeeId, Long storageId);
}
