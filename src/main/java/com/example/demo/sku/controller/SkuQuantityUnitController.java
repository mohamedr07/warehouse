package com.example.demo.sku.controller;

import com.example.demo.sku.dto.SkuQuantityUnitDto;
import com.example.demo.sku.model.Sku;
import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.sku.service.SkuQuantityUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skuquantityunit")
public class SkuQuantityUnitController {

    @Autowired
    private SkuQuantityUnitService skuQuantityUnitService;


    //  GET ALL SKU QUANTITY UNITS
    @GetMapping()
    public ResponseEntity<List<SkuQuantityUnitDto>> getSkuQuantityUnits() {
        List<SkuQuantityUnitDto> skuQuantityUnitDtos = skuQuantityUnitService.getSkuQuantityUnits();
        if (skuQuantityUnitDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(skuQuantityUnitDtos, HttpStatus.OK);
    }

    //  GET AN SKU QUANTITY UNIT
    @GetMapping(path = "{skuQuantityUnitId}")
    public ResponseEntity<SkuQuantityUnitDto> getSkuQuantityUnit(@PathVariable("skuQuantityUnitId") Long skuQuantityUnitId) {
        SkuQuantityUnitDto skuQuantityUnitDto = skuQuantityUnitService.getSkuQuantityUnit(skuQuantityUnitId);
        if (skuQuantityUnitDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(skuQuantityUnitDto, HttpStatus.OK);
    }

    //  UPDATE AN SKU QUANTITY UNIT
    @PutMapping(path = "{skuQuantityUnitId}")
    public ResponseEntity<SkuQuantityUnitDto> updateSkuQuantityUnit(@PathVariable("skuQuantityUnitId") Long skuQuantityUnitId, @RequestBody SkuQuantityUnitDto skuQuantityUnitDto) {
        skuQuantityUnitDto = skuQuantityUnitService.updateSkuQuantityUnit(skuQuantityUnitId, skuQuantityUnitDto);
        if (skuQuantityUnitDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(skuQuantityUnitDto, HttpStatus.OK);
    }

}
