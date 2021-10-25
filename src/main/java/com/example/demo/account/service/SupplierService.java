package com.example.demo.account.service;

import com.example.demo.account.dto.SupplierDto;
import com.example.demo.account.dto.UpdateSupplierDto;
import com.example.demo.account.model.Supplier;
import com.example.demo.account.repository.SupplierRepository;
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
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public SupplierService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    // ADD NEW SUPPLIER
    public SupplierDto createSupplier(SupplierDto supplierDto) {
        Optional<Supplier> supplierOptional = supplierRepository.findSupplierByEmailOrPhoneNumber(supplierDto.getEmail(), supplierDto.getPhoneNumber());
        if(supplierOptional.isPresent()) {
            throw new IllegalStateException("Email or Phone already exists");
        }
        Supplier supplier = new Supplier(supplierDto.getFullName(), supplierDto.getEmail(), supplierDto.getPassword(), supplierDto.getPhoneNumber(), supplierDto.getWarehouseLocation());
        supplier.setHashedPassword(passwordEncoder.encode(supplier.getHashedPassword()));
        supplier = supplierRepository.save(supplier);
        return new SupplierDto(supplier.getFullName(), supplier.getEmail(), supplier.getPhoneNumber(), supplier.getWarehouseLocation());
    }

    //GET A SUPPLIER
    public SupplierDto getSupplier(Long supplierId) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        if(supplierOptional.isEmpty()) {
            throw new IllegalStateException("Supplier does not exists");
        }
        Supplier supplier = supplierOptional.get();
        return new SupplierDto(supplier.getFullName(), supplier.getEmail(), supplier.getPhoneNumber(), supplier.getWarehouseLocation());
    }


    // GET SUPPLIERS
    public List<SupplierDto> getSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierDto> supplierDtos = new ArrayList<>();
        for(Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getFullName(), supplier.getEmail(), supplier.getPhoneNumber(), supplier.getWarehouseLocation()));
        }
        return supplierDtos;
    }

    // UPDATE SUPPLIER
    @Transactional
    public SupplierDto updateSupplier(HttpServletRequest request, Long supplierId, UpdateSupplierDto updateSupplierDto) {

        String emailFromToken = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);

        if(supplierOptional.isEmpty()) {
            throw new IllegalStateException("Supplier Does Not Exist");
        }
        Supplier supplier = supplierOptional.get();

        if(!Objects.equals(supplier.getEmail(), emailFromToken)) {
            throw new IllegalStateException("NOT YOUR ACCOUNT TO EDIT");
        }

        supplier.setWarehouseLocation(updateSupplierDto.getWarehouseLocation());
        return new SupplierDto(supplier.getFullName(), supplier.getEmail(), supplier.getPhoneNumber(), supplier.getWarehouseLocation());
    }

    // DELETE SUPPLIER
    public void deleteSupplier(Long supplierId) {
        boolean exists = supplierRepository.existsById(supplierId);
        if(!exists) {
            throw new IllegalStateException("Supplier with id " + supplierId + " does not exists");
        }
        supplierRepository.deleteById(supplierId);
    }
}
