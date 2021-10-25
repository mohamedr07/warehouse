package com.example.demo.sku.dto;

import javax.validation.constraints.NotEmpty;

public class DefaultUnitDto {

    @NotEmpty
    private Long skuQuantityUnitId;

    public DefaultUnitDto() {
    }

    public DefaultUnitDto(Long skuQuantityUnitId) {
        this.skuQuantityUnitId = skuQuantityUnitId;
    }

    public Long getSkuQuantityUnitId() {
        return skuQuantityUnitId;
    }

    public void setSkuQuantityUnitId(Long skuQuantityUnitId) {
        this.skuQuantityUnitId = skuQuantityUnitId;
    }

    @Override
    public String toString() {
        return "DefaultUnitDto{" +
                "skuQuantityUnitId=" + skuQuantityUnitId +
                '}';
    }
}
