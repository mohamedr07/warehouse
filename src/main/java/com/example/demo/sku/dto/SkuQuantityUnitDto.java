package com.example.demo.sku.dto;

import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.utility.model.Dimension;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

public class SkuQuantityUnitDto {

    private Long id;

    @NotEmpty
    @Size(min = 4)
    private String name;

    @NotEmpty
    private Double price;

    @NotBlank
    private Dimension dimension;

    public SkuQuantityUnitDto() {
    }

    public SkuQuantityUnitDto(SkuQuantityUnit skuQuantityUnit) {
        this.id = skuQuantityUnit.getId();
        this.name = skuQuantityUnit.getName();
        this.price = skuQuantityUnit.getPrice();
        this.dimension = skuQuantityUnit.getDimension();
    }

    public SkuQuantityUnitDto(String name, Double price, Dimension dimension) {
        this.name = name;
        this.price = price;
        this.dimension = dimension;
    }

    public SkuQuantityUnitDto(Long id, String name, Double price, Dimension dimension) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.dimension = dimension;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "SkuQuantityUnitDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", dimension=" + dimension +
                '}';
    }
}
