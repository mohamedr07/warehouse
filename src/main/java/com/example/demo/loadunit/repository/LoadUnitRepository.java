package com.example.demo.loadunit.repository;

import com.example.demo.loadunit.model.LoadUnit;
import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.utility.enums.LoadUnitStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoadUnitRepository extends JpaRepository<LoadUnit, Long> {
    List<LoadUnit> findByStatus(LoadUnitStatus status);
    List<LoadUnit> findBySkuQuantityUnit(SkuQuantityUnit skuQuantityUnit);
    List<LoadUnit> findBySkuQuantityUnitAndStatusOrSkuQuantityUnitIsNullOrderBySkuQuantityUnit(SkuQuantityUnit skuQuantityUnit, LoadUnitStatus status);
}
