package com.example.demo.Inventory.repository;

import com.example.demo.Inventory.model.Inventory;
import com.example.demo.sku.model.Sku;
import com.example.demo.sku.model.SkuQuantityUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuAndSkuQuantityUnit(Sku sku, SkuQuantityUnit skuQuantityUnit);
}
