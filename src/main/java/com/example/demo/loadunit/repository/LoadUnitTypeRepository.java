package com.example.demo.loadunit.repository;

import com.example.demo.loadunit.model.LoadUnit;
import com.example.demo.loadunit.model.LoadUnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoadUnitTypeRepository extends JpaRepository<LoadUnitType, Long> {
    LoadUnitType findByName(String name);
}
