package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>
{
    List<Delivery> findDeliveriesByStorageId(Long storageId);
    Optional<Delivery> findByIdAndStorageId(Long deliveryId, Long storageId);
}
