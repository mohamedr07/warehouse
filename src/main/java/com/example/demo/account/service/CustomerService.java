package com.example.demo.account.service;

import com.example.demo.account.dto.CustomerDto;
import com.example.demo.account.dto.UpdateCustomerDto;
import com.example.demo.account.model.Customer;
import com.example.demo.account.repository.CustomerRepository;
import com.example.demo.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public CustomerService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // ADD NEW CUSTOMER
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Optional<Customer> customerOptional = customerRepository.findCustomerByEmailOrPhoneNumber(customerDto.getEmail(), customerDto.getPhoneNumber());
        if(customerOptional.isPresent()) {
            throw new IllegalStateException("Email or Phone already exists");
        }
        Customer customer = new Customer(customerDto.getFullName(), customerDto.getEmail(), customerDto.getPassword(), customerDto.getPhoneNumber(), customerDto.getAddress());
        customer.setHashedPassword(passwordEncoder.encode(customer.getHashedPassword()));
        customer = customerRepository.save(customer);
        return new CustomerDto(customer.getFullName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress());
    }

    //GET A CUSTOMER
    public CustomerDto getCustomer(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isEmpty()) {
            throw new IllegalStateException("Customer does not exists");
        }
        Customer customer = customerOptional.get();
        return new CustomerDto(customer.getFullName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress());
    }

    // GET CUSTOMERS
    public List<CustomerDto> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer customer : customers) {
            customerDtos.add(new CustomerDto(customer.getFullName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress()));
        }
        return customerDtos;
    }

    // UPDATE CUSTOMER
    @Transactional
    public CustomerDto updateCustomer(Long customerId, UpdateCustomerDto updateCustomerDto) {

        String emailFromToken = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();

        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isEmpty()) {
            throw new IllegalStateException("Customer Does Not Exist");
        }

        Customer customer = customerOptional.get();

        if(!Objects.equals(customer.getEmail(), emailFromToken)) {
            throw new IllegalStateException("NOT YOUR ACCOUNT TO EDIT");
        }

        customer.setAddress(updateCustomerDto.getAddress());
        return new CustomerDto(customer.getFullName(), customer.getEmail(), customer.getPhoneNumber(), customer.getAddress());
    }

    // DELETE CUSTOMER
    public void deleteCustomer(Long customerId) {
        boolean exists = customerRepository.existsById(customerId);
        if(!exists) {
            throw new IllegalStateException("Customer with id " + customerId + " does not exists");
        }
        customerRepository.deleteById(customerId);
    }
}
