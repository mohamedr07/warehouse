package com.example.demo.sku.dto;

import com.example.demo.sku.model.Sku;
import com.example.demo.sku.model.SkuQuantityUnit;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

public class SkuDto {

    @NotEmpty
    @Size(min = 4)
    private String name;
    private String description;
    private Set<SkuQuantityUnitDto> skuQuantityUnitDtos;
    private DefaultUnitDto defaultUnitDto;

    public SkuDto() {
    }

    public SkuDto(String name, String description, Set<SkuQuantityUnitDto> skuQuantityUnitDtos) {
        this.name = name;
        this.description = description;
        this.skuQuantityUnitDtos = skuQuantityUnitDtos;
    }

    public SkuDto(Sku sku) {
        this.name = sku.getName();
        this.description = sku.getDescription();
        this.skuQuantityUnitDtos = getSkuQuantityUnitDtos(sku.getSkuQuantityUnits());
        if(sku.getSkuDefaultUnit() == null) {
            return;
        }

        this.defaultUnitDto = new DefaultUnitDto(sku.getSkuDefaultUnit().getId());
    }

    public Set<SkuQuantityUnitDto> getSkuQuantityUnitDtos(Set<SkuQuantityUnit> skuQuantityUnits) {
        Set<SkuQuantityUnitDto> skuQuantityUnitDtos = new HashSet<>();
        for(SkuQuantityUnit skuQuantityUnit : skuQuantityUnits) {
            skuQuantityUnitDtos.add(new SkuQuantityUnitDto(skuQuantityUnit.getId(), skuQuantityUnit.getName(), skuQuantityUnit.getPrice(), skuQuantityUnit.getDimension()));
        }
        return skuQuantityUnitDtos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SkuQuantityUnitDto> getSkuQuantityUnitDtos() {
        return skuQuantityUnitDtos;
    }

    public void setSkuQuantityUnitDtos(Set<SkuQuantityUnitDto> skuQuantityUnitDtos) {
        this.skuQuantityUnitDtos = skuQuantityUnitDtos;
    }

    public DefaultUnitDto getDefaultUnitDto() {
        return defaultUnitDto;
    }

    public void setDefaultUnitDto(DefaultUnitDto defaultUnitDto) {
        this.defaultUnitDto = defaultUnitDto;
    }

    @Override
    public String toString() {
        return "SkuDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", skuQuantityUnitDtos=" + skuQuantityUnitDtos +
                ", defaultUnitDto=" + defaultUnitDto +
                '}';
    }
}
