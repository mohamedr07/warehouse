package com.example.demo.sku.controller;

import com.example.demo.sku.dto.DefaultUnitDto;
import com.example.demo.utility.dto.PageableDto;
import com.example.demo.sku.dto.SkuDto;
import com.example.demo.sku.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/sku")
public class SkuController {

    @Autowired
    private SkuService skuService;

//  ADD NEW SKU
    @PostMapping()
    @Secured({"ALL-AUTH"})
    public ResponseEntity<SkuDto> createSku(@RequestBody @Valid SkuDto skuDto) {
        try {
            skuDto = skuService.createSku(skuDto);
            return new ResponseEntity<>(skuDto, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    //  GET ALL SKUS
    @GetMapping()
    public ResponseEntity<List<SkuDto>> getSkus(@RequestBody PageableDto pageableDto) {
        List<SkuDto> skuDtos = skuService.getSkus(pageableDto);
        if (skuDtos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(skuDtos, HttpStatus.OK);
    }

//  GET AN SKU
    @GetMapping(path = "{skuId}")
    public ResponseEntity<SkuDto> getSku(@PathVariable("skuId") Long skuId) {
        SkuDto skuDto = skuService.getSku(skuId);
        if (skuDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(skuDto, HttpStatus.OK);
    }

//  UPDATE AN SKU
    @PutMapping(path = "{skuId}")
        public ResponseEntity<SkuDto> updateSku(@PathVariable("skuId") Long skuId, @Valid @RequestBody SkuDto skuDto) {
        skuDto = skuService.updateSku(skuId, skuDto);
        if (skuDto == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(skuDto, HttpStatus.OK);
    }

//  DELETE AN SKU
    @DeleteMapping(path = "{skuId}")
    public ResponseEntity<HttpStatus> deleteSku(@PathVariable("skuId") long skuId) {
        try {
            skuService.deleteSku(skuId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
//  SET SKU DEFAULT UNIT
    @PutMapping(path = "/setdefaultunit/{skuId}")
    public ResponseEntity<SkuDto> setSkuDefaultUnit(@PathVariable("skuId") Long skuId, @RequestBody DefaultUnitDto setDefaultUnitDto) {
        SkuDto skuDto = skuService.setSkuDefaultUnit(skuId, setDefaultUnitDto);
        if (skuDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(skuDto, HttpStatus.OK);
    }
}
