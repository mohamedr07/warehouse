package com.example.demo.configuration.repository;

import com.example.demo.configuration.model.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
    Configuration findByName(String name);
}
