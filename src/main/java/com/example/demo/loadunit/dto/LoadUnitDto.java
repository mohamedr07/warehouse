package com.example.demo.loadunit.dto;

import com.example.demo.loadunit.model.LoadUnit;
import com.example.demo.loadunit.model.LoadUnitType;
import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.utility.enums.LoadUnitStatus;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

public class LoadUnitDto {

    private LoadUnitStatus status;
    private LoadUnitTypeDto loadUnitTypeDto;
    private SkuQuantityUnit skuQuantityUnit;
    private Long quantity;


    public LoadUnitDto() {
    }

    public LoadUnitDto(LoadUnitStatus status, LoadUnitTypeDto loadUnitTypeDto, SkuQuantityUnit skuQuantityUnit, Long quantity) {
        this.status = status;
        this.loadUnitTypeDto = loadUnitTypeDto;
        this.skuQuantityUnit = skuQuantityUnit;
        this.quantity = quantity;
    }

    public LoadUnitDto(LoadUnit loadUnit) {
        this.status = loadUnit.getStatus();
        this.loadUnitTypeDto = new LoadUnitTypeDto(loadUnit.getLoadUnitType());
        this.skuQuantityUnit = loadUnit.getSkuQuantityUnit();
        this.quantity = loadUnit.getQuantity();
    }

    public LoadUnitStatus getStatus() {
        return status;
    }

    public void setStatus(LoadUnitStatus status) {
        this.status = status;
    }

    public LoadUnitTypeDto getLoadUnitTypeDto() {
        return loadUnitTypeDto;
    }

    public void setLoadUnitTypeDto(LoadUnitTypeDto loadUnitTypeDto) {
        this.loadUnitTypeDto = loadUnitTypeDto;
    }

    public SkuQuantityUnit getSkuQuantityUnit() {
        return skuQuantityUnit;
    }

    public void setSkuQuantityUnit(SkuQuantityUnit skuQuantityUnit) {
        this.skuQuantityUnit = skuQuantityUnit;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LoadUnitDto{" +
                "status=" + status +
                ", loadUnitTypeDto=" + loadUnitTypeDto +
                ", skuQuantityUnit=" + skuQuantityUnit +
                ", quantity=" + quantity +
                '}';
    }
}
