package com.example.magazyn.Repository;

import com.example.magazyn.Entity.Delivery;
import com.example.magazyn.Entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageRepository extends JpaRepository<Storage, Long>
{
    @Query("select d from Delivery d join fetch d.storage s where s.id = :storageId")
    List<Delivery> findAllDeliveriesById(@Param("storageId") Long storageId);
}

