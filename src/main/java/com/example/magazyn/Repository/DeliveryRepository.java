package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>
{
    @Query("select distinct d from Delivery d " +
            "join fetch d.deliveryProducts products " +
            "join fetch products.product product " +
            "join fetch d.storage storage " +
            "where product.id = :productId")
    List<Delivery> findAllDeliveriesWithProductId(@Param("productId") Long productId);
}
