package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>
{
    List<Employee> findEmployeesByStorageIdAndFireDateIsNull(Long storageId);
    Optional<Employee> findByPersonIdAndFireDateIsNull(Long personId);
}
