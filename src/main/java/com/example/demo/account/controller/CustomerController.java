package com.example.demo.account.controller;

import com.example.demo.account.dto.CustomerDto;
import com.example.demo.account.dto.UpdateCustomerDto;
import com.example.demo.account.model.Customer;
import com.example.demo.account.repository.CustomerRepository;
import com.example.demo.account.service.CustomerService;
import com.example.demo.sku.model.Sku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    // ADD CUSTOMER
    @PostMapping("/add")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody @Valid CustomerDto customerDto) {
        try {
            return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  GET ALL CUSTOMERS
    @GetMapping()
    public ResponseEntity<List<CustomerDto>> getCustomers() {
        List<CustomerDto> customerDtos = customerService.getCustomers();
        if (customerDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    //  GET CUSTOMER
    @GetMapping(path = "{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") Long customerId) {
        CustomerDto customerOptional = customerService.getCustomer(customerId);
        if (customerOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerOptional, HttpStatus.OK);
    }

    //  UPDATE CUSTOMER
    @PutMapping(path = "{customerId}")
    public ResponseEntity<CustomerDto> updateCustomer(HttpServletRequest request, @PathVariable("customerId") Long customerId, @RequestBody @Valid UpdateCustomerDto updateCustomerDto) {
        CustomerDto customerDto = customerService.updateCustomer(customerId, updateCustomerDto);
        if (customerDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    //  DELETE CUSTOMER
    @DeleteMapping(path = "{customerId}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("customerId") Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
