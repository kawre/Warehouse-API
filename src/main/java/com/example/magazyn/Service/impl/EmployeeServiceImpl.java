package com.example.magazyn.Service.impl;

import com.example.magazyn.DTO.EmployeeDTO;
import com.example.magazyn.Entity.Employee;
import com.example.magazyn.Entity.Person;
import com.example.magazyn.Entity.Storage;
import com.example.magazyn.Mappers.EmployeeMapper;
import com.example.magazyn.Repository.EmployeeRepository;
import com.example.magazyn.Service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService
{
    private final EmployeeRepository employeeRepository;
    private final PersonServiceImpl personService;
    private final StorageServiceImpl storageService;

    @Override
    public Employee findById(Long id)
    {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Employee not found"));
    }

    @Override
    public boolean isEmployed(Long personId)
    {
        Optional<Employee> employee = this.employeeRepository.findByPersonIdAndFireDateIsNull(personId);
        return employee.isPresent();
    }

    @Override
    public EmployeeDTO findByIdAndStorageId(Long employeeId, Long storageId)
    {
        Employee employee = this.findById(employeeId);

        if (!employee.getStorage().getId().equals(storageId))
            throw new ResponseStatusException(BAD_REQUEST, "Employee is employed in a different storage");

        return EmployeeMapper.instance.toDTO(employee);
    }

    @Override
    public List<Employee> findEmployeesByStorageIdAndFireDateIsNull(Long storageId)
    {
        return this.employeeRepository.findEmployeesByStorageIdAndFireDateIsNull(storageId);
    }

    @Override
    public Employee hire(Long personId, Long storageId)
    {
        Storage storage = this.storageService.findById(storageId);
        Person person = this.personService.findById(personId);

        if (this.isEmployed(personId))
            throw new ResponseStatusException(CONFLICT, "Person is already employed");

        Employee employee = new Employee();
        employee.setPerson(person);
        employee.setStorage(storage);
        employee.setHireDate(Date.valueOf(LocalDate.now()));
        employee.setFireDate(null);

        return this.employeeRepository.save(employee);
    }

    @Override
    public Employee fire(Long employeeId, Long storageId)
    {
        Employee employee = this.findById(employeeId);

        if (!employee.getStorage().getId().equals(storageId))
            throw new ResponseStatusException(BAD_REQUEST, "Employee is hired in a different warehouse");

        if (employee.getFireDate() != null)
            throw new ResponseStatusException(CONFLICT, "Employee is no longer employed");

        employee.setFireDate(Date.valueOf(LocalDate.now()));
        return this.employeeRepository.save(employee);
    }
}
