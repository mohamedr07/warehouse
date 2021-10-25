package com.example.demo.loadunit.dto;

import com.example.demo.loadunit.model.LoadUnitType;
import com.example.demo.utility.model.Dimension;

import javax.validation.constraints.NotBlank;

public class LoadUnitTypeDto {

    @NotBlank
    private String name;

    @NotBlank
    private Dimension dimension;

    private Long quantity;


    public LoadUnitTypeDto() {
    }

    public LoadUnitTypeDto(String name, Dimension dimension, Long quantity) {
        this.name = name;
        this.dimension = dimension;
        this.quantity = quantity;
    }

    public LoadUnitTypeDto(LoadUnitType loadUnitType) {
        this.name = loadUnitType.getName();
        this.dimension = loadUnitType.getDimension();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "LoadUnitTypeDto{" +
                "name='" + name + '\'' +
                ", dimension=" + dimension +
                '}';
    }
}
