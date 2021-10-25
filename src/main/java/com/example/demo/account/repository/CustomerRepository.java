package com.example.demo.account.repository;

import com.example.demo.account.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findCustomerByEmailOrPhoneNumber(String email, String phoneNumber);
}
