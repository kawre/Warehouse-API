package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryRepository extends JpaRepository<Delivery, Long>
{
    @Query("select d from Delivery d join fetch d.storage s where s.id = ?1")
    List<Delivery> findAllByStorageId(Long storageId);

    @Query("select d from Delivery d join fetch d.storage s where d.id = ?1 and s.id = ?2")
    Optional<Delivery> findByIdAndStorageId(Long deliveryId, Long storageId);

}
