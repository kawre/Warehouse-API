package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Long>
{
    Optional<Company> findByVatin(String vatin);
}
