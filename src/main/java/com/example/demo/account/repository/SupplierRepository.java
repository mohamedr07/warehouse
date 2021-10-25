package com.example.demo.account.repository;

import com.example.demo.account.model.Customer;
import com.example.demo.account.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
    Optional<Supplier> findSupplierByEmailOrPhoneNumber(String email, String phoneNumber);
}
