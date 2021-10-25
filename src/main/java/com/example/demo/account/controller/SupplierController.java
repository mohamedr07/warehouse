package com.example.demo.account.controller;

import com.example.demo.account.dto.SupplierDto;
import com.example.demo.account.dto.UpdateSupplierDto;
import com.example.demo.account.dto.UpdateSupplierDto;
import com.example.demo.account.model.Supplier;
import com.example.demo.account.model.Supplier;
import com.example.demo.account.service.SupplierService;
import com.example.demo.account.service.SupplierService;
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

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {


    @Autowired
    private SupplierService supplierService;

    // ADD SUPPLIER
    @PostMapping("/add")
    public ResponseEntity<SupplierDto> createSupplier(@RequestBody @Valid SupplierDto supplierDto) {
        try {
            return new ResponseEntity<>(supplierService.createSupplier(supplierDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  GET ALL SUPPLIERS
    @GetMapping()
    public ResponseEntity<List<SupplierDto>> getSuppliers() {
        List<SupplierDto> supplierDtos = supplierService.getSuppliers();
        if (supplierDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(supplierDtos, HttpStatus.OK);
    }

    //  GET SUPPLIER
    @GetMapping(path = "{supplierId}")
    public ResponseEntity<SupplierDto> getSupplier(@PathVariable("supplierId") Long supplierId) {
        SupplierDto supplierOptional = supplierService.getSupplier(supplierId);
        if (supplierOptional == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplierOptional, HttpStatus.OK);
    }

    //  UPDATE SUPPLIER
    @PutMapping(path = "{supplierId}")
    public ResponseEntity<SupplierDto> updateSupplier(HttpServletRequest request, @PathVariable("supplierId") Long supplierId, @RequestBody @Valid UpdateSupplierDto updateSupplierDto) {
        SupplierDto supplierDto = supplierService.updateSupplier(request, supplierId, updateSupplierDto);
        if (supplierDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplierDto, HttpStatus.OK);
    }

    //  DELETE AN SKU
    @DeleteMapping(path = "{supplierId}")
    public ResponseEntity<HttpStatus> deleteSupplier(@PathVariable("supplierId") Long supplierId) {
        try {
            supplierService.deleteSupplier(supplierId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
