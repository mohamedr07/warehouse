package com.example.demo.sku.service;

import com.example.demo.sku.dto.SkuQuantityUnitDto;
import com.example.demo.sku.model.Sku;
import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.sku.repository.SkuQuantityUnitRepository;
import com.example.demo.sku.repository.SkuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SkuQuantityUnitService {

    @Autowired
    private SkuQuantityUnitRepository skuQuantityUnitRepository;
    @Autowired
    private SkuRepository skuRepository;

    public SkuQuantityUnitDto getSkuQuantityUnit(Long unitId) {
        Optional<SkuQuantityUnit> skuQuantityUnitOptional = skuQuantityUnitRepository.findById(unitId);
        if(skuQuantityUnitOptional.isEmpty()) {
            throw new IllegalStateException("SKU Quantity Unit Does Not Exist");
        }
        return new SkuQuantityUnitDto(skuQuantityUnitOptional.get());
    }

    public List<SkuQuantityUnitDto> getSkuQuantityUnits() {
        List<SkuQuantityUnit> skuQuantityUnits = skuQuantityUnitRepository.findAll();
        List<SkuQuantityUnitDto> skuQuantityUnitDtos = new ArrayList<>();
        for(SkuQuantityUnit skuQuantityUnit : skuQuantityUnits) {
            skuQuantityUnitDtos.add(new SkuQuantityUnitDto(skuQuantityUnit));
        }
        return skuQuantityUnitDtos;
    }


    @Transactional
    public SkuQuantityUnitDto updateSkuQuantityUnit(Long skuQuantityUnitId, SkuQuantityUnitDto skuQuantityUnitDto) {
        Optional<SkuQuantityUnit> skuQuantityUnitOptional = skuQuantityUnitRepository.findById(skuQuantityUnitId);
        if(skuQuantityUnitOptional.isEmpty()) {
            throw new IllegalStateException("SKU Quantity Unit Does Not Exist");
        }
        SkuQuantityUnit skuQuantityUnit = skuQuantityUnitOptional.get();
        skuQuantityUnit.setName(skuQuantityUnitDto.getName());
        skuQuantityUnit.setPrice(skuQuantityUnitDto.getPrice());
        skuQuantityUnit.setDimension(skuQuantityUnitDto.getDimension());
        return new SkuQuantityUnitDto(skuQuantityUnit);
    }


}
