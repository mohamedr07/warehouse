package com.example.demo.sku.repository;

import com.example.demo.sku.model.Sku;
import com.example.demo.sku.model.SkuQuantityUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkuQuantityUnitRepository extends JpaRepository<SkuQuantityUnit, Long> {
}
