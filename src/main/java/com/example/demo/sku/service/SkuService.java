package com.example.demo.sku.service;

import com.example.demo.sku.dto.DefaultUnitDto;
import com.example.demo.sku.dto.SkuDto;
import com.example.demo.sku.model.Sku;
import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.sku.repository.SkuQuantityUnitRepository;
import com.example.demo.sku.repository.SkuRepository;
import com.example.demo.utility.dto.PageableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
public class SkuService {

    @Autowired
    private SkuRepository skuRepository;

    @Autowired
    private SkuQuantityUnitRepository skuQuantityUnitRepository;



    public SkuDto createSku(SkuDto skuDto) {
        Sku sku = new Sku(skuDto);
        sku = skuRepository.save(sku);
        return new SkuDto(sku);
    }


    public SkuDto getSku(Long skuId) {
        Optional<Sku> skuOptional = skuRepository.findById(skuId);
        if (skuOptional.isEmpty()) {
            throw new IllegalStateException("Sku with id " + skuId + " does not exists");
        }
        return new SkuDto(skuOptional.get());
    }

    public List<SkuDto> getSkus(PageableDto pageableDto) {
        Page<Sku> skus = skuRepository.findAll(PageRequest.of(pageableDto.getPageNumber(), pageableDto.getNumberOfInstances()));
        List<SkuDto> skuDtos = new ArrayList<>();
        for (Sku sku : skus) {
            skuDtos.add(new SkuDto(sku));
        }
        return skuDtos;
    }


    public SkuDto updateSku(Long skuId, SkuDto skuDto) {

        Optional<Sku> skuOptional = skuRepository.findById(skuId);
        if (skuOptional.isEmpty()) {
            throw new IllegalStateException("Sku with id " + skuId + " does not exists");
        }

        Sku sku = skuOptional.get();
        Sku skuUpdateData = new Sku(skuDto);

        sku.setName(skuUpdateData.getName());
        sku.setDescription(skuUpdateData.getDescription());
        sku.setSkuDefaultUnit(null);

        Set<SkuQuantityUnit> skuQuantityUnits = new HashSet<>();
        skuQuantityUnits.addAll(sku.getSkuQuantityUnits());

        if(skuUpdateData.getSkuQuantityUnits() == null) {
            return new SkuDto(skuRepository.save(sku));
        }

        Set<SkuQuantityUnit> skuQuantityUnitsUpdated = new HashSet<>();
        skuQuantityUnitsUpdated.addAll(skuUpdateData.getSkuQuantityUnits());


        for(SkuQuantityUnit skuQuantityUnit : skuQuantityUnits) {
            if(!skuUpdateData.getSkuQuantityUnits().contains(skuQuantityUnit)) {
                sku.getSkuQuantityUnits().remove(skuQuantityUnit);
            }
        }

//        for(SkuQuantityUnit skuQuantityUnit : skuQuantityUnitsUpdated) {
//            if(!(sku.getSkuQuantityUnits().contains(skuQuantityUnit))) {
//                sku.getSkuQuantityUnits().add(skuQuantityUnit);
//            }
//        }

        sku.getSkuQuantityUnits().addAll(skuUpdateData.getSkuQuantityUnits());
        sku = skuRepository.save(sku);
        return new SkuDto(sku);
    }

    public void deleteSku(Long skuId) {
        boolean exists = skuRepository.existsById(skuId);
        if (!exists) {
            throw new IllegalStateException("Sku with id " + skuId + " does not exists");
        }
        skuRepository.deleteById(skuId);
    }

    @Transactional
    public SkuDto setSkuDefaultUnit(Long skuId, DefaultUnitDto setDefaultUnitDto) {
        Optional<Sku> skuOptional = skuRepository.findById(skuId);
        Sku sku;
        if (skuOptional.isEmpty()) {
            throw new IllegalStateException("SKU Does Not Exist");
        }
        sku = skuOptional.get();
        for(SkuQuantityUnit skuQuantityUnit : sku.getSkuQuantityUnits()) {
            if(skuQuantityUnit.getId() == setDefaultUnitDto.getSkuQuantityUnitId()) {
                sku.setSkuDefaultUnit(skuQuantityUnit);
            }
        }
        return new SkuDto(sku);
    }
}
