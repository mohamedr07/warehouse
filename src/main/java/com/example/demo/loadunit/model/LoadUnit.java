package com.example.demo.loadunit.model;


import com.example.demo.loadunit.dto.LoadUnitDto;
import com.example.demo.loadunit.dto.LoadUnitTypeDto;
import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.utility.enums.LoadUnitStatus;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = LoadUnit.TABLE_NAME)
public class LoadUnit {

    final static String TABLE_NAME = "LOAD_UNIT";

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private LoadUnitStatus status;

    @ManyToOne()
    @JoinColumn(name = "LOAD_UNIT_ID")
    private LoadUnitType loadUnitType;

    @ManyToOne()
    @JoinColumn(name = "SKU_QUANTITY_UNIT_ID")
    private SkuQuantityUnit skuQuantityUnit;

    @Column(name = "QUANTITY")
    private Long quantity;

    public LoadUnit() {
    }

    public LoadUnit(LoadUnitStatus status, LoadUnitType loadUnitType, SkuQuantityUnit skuQuantityUnit, Long quantity) {
        this.status = status;
        this.loadUnitType = loadUnitType;
        this.skuQuantityUnit = skuQuantityUnit;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LoadUnitStatus getStatus() {
        return status;
    }

    public void setStatus(LoadUnitStatus status) {
        this.status = status;
    }

    public LoadUnitType getLoadUnitType() {
        return loadUnitType;
    }

    public void setLoadUnitType(LoadUnitType loadUnitType) {
        this.loadUnitType = loadUnitType;
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
        return "LoadUnit{" +
                "id=" + id +
                ", status=" + status +
                ", loadUnitType=" + loadUnitType +
                ", skuQuantityUnit=" + skuQuantityUnit +
                ", quantity=" + quantity +
                '}';
    }
}
