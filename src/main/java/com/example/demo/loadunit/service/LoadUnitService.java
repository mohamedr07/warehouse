package com.example.demo.loadunit.service;

import com.example.demo.loadunit.dto.LoadUnitDto;
import com.example.demo.loadunit.dto.LoadUnitTypeDto;
import com.example.demo.loadunit.model.LoadUnit;
import com.example.demo.loadunit.model.LoadUnitType;
import com.example.demo.loadunit.repository.LoadUnitRepository;
import com.example.demo.loadunit.repository.LoadUnitTypeRepository;
import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.utility.enums.LoadUnitStatus;
import com.example.demo.utility.model.Dimension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoadUnitService {

    @Autowired
    private LoadUnitRepository loadUnitRepository;

    @Autowired
    private LoadUnitTypeRepository loadUnitTypeRepository;

    public LoadUnitDto addLoadUnit(LoadUnitTypeDto loadUnitTypeDto) {

        LoadUnitType loadUnitType = loadUnitTypeRepository.findByName(loadUnitTypeDto.getName());
        if(loadUnitType == null) {
            loadUnitType = new LoadUnitType(loadUnitTypeDto);
            loadUnitType = loadUnitTypeRepository.save(loadUnitType);
        }

        LoadUnit loadUnit = new LoadUnit(LoadUnitStatus.NotFull, loadUnitType, null, 0L);
        loadUnit = loadUnitRepository.save(loadUnit);
        return new LoadUnitDto(loadUnit);

    }

    public List<LoadUnitDto> addLoadUnits(LoadUnitTypeDto loadUnitTypeDto) {
        LoadUnitType loadUnitType = loadUnitTypeRepository.findByName(loadUnitTypeDto.getName());
        if(loadUnitType == null) {
            loadUnitType = new LoadUnitType(loadUnitTypeDto);
            loadUnitType = loadUnitTypeRepository.save(loadUnitType);
        }
        List<LoadUnit> loadUnits = new ArrayList<>();
        LoadUnit loadUnit;
        for(int i = 0; i < loadUnitTypeDto.getQuantity(); i++) {
            loadUnit = new LoadUnit(LoadUnitStatus.NotFull, loadUnitType, null, 0L);
            loadUnits.add(loadUnit);

        }
        loadUnits = loadUnitRepository.saveAll(loadUnits);
        List<LoadUnitDto> loadUnitDtos = new ArrayList<>();
        for(LoadUnit loadUnit1 : loadUnits) {
            loadUnitDtos.add(new LoadUnitDto(loadUnit1));
        }
        return loadUnitDtos;
    }


    public void addSkus(SkuQuantityUnit skuQuantityUnit, Long quantity) throws RuntimeException {
        List<LoadUnit> loadUnits = loadUnitRepository.findBySkuQuantityUnitAndStatusOrSkuQuantityUnitIsNullOrderBySkuQuantityUnit(skuQuantityUnit, LoadUnitStatus.NotFull);
        Dimension dimension = skuQuantityUnit.getDimension();
        long maxLoadUnitQuantity;
        long loadUnitQuantity;
        int skuVolume = dimension.getHeight() * dimension.getLength() * dimension.getWidth();
        int loadUnitVolume;

        for(LoadUnit loadUnit : loadUnits) {
            loadUnitQuantity = 0L;
            dimension = loadUnit.getLoadUnitType().getDimension();
            loadUnitVolume = dimension.getHeight() * dimension.getLength() * dimension.getWidth();

            if(loadUnit.getQuantity() > 0) {
                loadUnitQuantity = loadUnit.getQuantity();
                loadUnitVolume -= (skuVolume * loadUnit.getQuantity());
            }

            maxLoadUnitQuantity = loadUnitVolume / skuVolume;
            if(maxLoadUnitQuantity >= quantity) {
                loadUnitQuantity += quantity;
                quantity = 0L;
            } else {
                loadUnitQuantity += maxLoadUnitQuantity;
                quantity -= maxLoadUnitQuantity;
            }

            loadUnit.setSkuQuantityUnit(skuQuantityUnit);
            loadUnit.setQuantity(loadUnitQuantity);
            if(maxLoadUnitQuantity == loadUnitQuantity) {
                loadUnit.setStatus(LoadUnitStatus.Full);
            }
            loadUnitRepository.save(loadUnit);
            if(quantity == 0) {
                break;
            }
        }
    }

    public void getSkus(SkuQuantityUnit skuQuantityUnit, Long quantity) throws RuntimeException {
        List<LoadUnit> loadUnits = loadUnitRepository.findBySkuQuantityUnit(skuQuantityUnit);
        for(LoadUnit loadUnit : loadUnits) {
            if(quantity == 0L) {
                break;
            }

            if(loadUnit.getQuantity() >= quantity) {
                loadUnit.setQuantity(loadUnit.getQuantity() - quantity);
                quantity = 0L;
            } else {
                quantity -= loadUnit.getQuantity();
                loadUnit.setQuantity(0L);
            }

            if(loadUnit.getQuantity().equals(0L)) {
                loadUnit.setSkuQuantityUnit(null);
            }
            loadUnit.setStatus(LoadUnitStatus.NotFull);
            loadUnitRepository.save(loadUnit);

//            if(loadUni`t.getQuantity() >= quantity) {
//                loadUnit.setQuantity(loadUnit.getQuantity() - quantity);
//                if(loadUnit.getQuantity().equals(0L)) {
//                    loadUnit.setSkuQuantityUnit(null);
//                }
//                loadUnit.setStatus(LoadUnitStatus.NotFull);
//                loadUnitRepository.save(loadUnit);
//                break;
//            }
//
//            quantity -= loadUnit.getQuantity();
//            loadUnit.setQuantity(0L);
//            if(loadUnit.getQuantity().equals(0L)) {
//                loadUnit.setSkuQuantityUnit(null);
//            }
//            loadUnit.setStatus(LoadUnitStatus.NotFull);
//            loadUnitRepository.save(loadUnit);

        }
    }

}
