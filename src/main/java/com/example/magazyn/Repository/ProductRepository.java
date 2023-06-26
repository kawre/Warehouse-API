package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>
{
    @Query("select p from ReceiptProduct pr " +
            "join fetch pr.product p")
    List<Product> getAllProductsBoughtByCustomer(@Param("customerId") Long customerId);
}
