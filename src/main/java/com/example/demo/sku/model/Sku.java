package com.example.demo.sku.model;

import com.example.demo.sku.dto.SkuDto;
import com.example.demo.sku.dto.SkuQuantityUnitDto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = Sku.TABLE_NAME)
public class Sku {

    final static String TABLE_NAME = "SKU";

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false, length = 150)
    private String name;

    @Column(name = "DESCRIPTION", length = 500)
    private String description;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "SKU_ID")
    private Set<SkuQuantityUnit> skuQuantityUnits;

    @OneToOne(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
    @JoinColumn(name = "SKU_DEFAULT_UNIT")
    private SkuQuantityUnit skuDefaultUnit;

    public Sku() {
        skuQuantityUnits = new HashSet<>();
    }

    public Sku(String name, String description, Set<SkuQuantityUnit> skuQuantityUnits) {
        this();
        this.name = name;
        this.description = description;
        if(skuQuantityUnits != null) {
            this.skuQuantityUnits = skuQuantityUnits;
        }
    }

    public Sku(Long id, String name, String description, Set<SkuQuantityUnit> skuQuantityUnits) {
        this();
        this.id = id;
        this.name = name;
        this.description = description;
        if(skuQuantityUnits != null) {
            this.skuQuantityUnits = skuQuantityUnits;
        }
    }

    public Sku(SkuDto skuDto) {
        this();
        this.name = skuDto.getName();
        this.description = skuDto.getDescription();
        this.skuQuantityUnits = getSkuQuantityUnitsFromDto(skuDto.getSkuQuantityUnitDtos());
    }

    public Set<SkuQuantityUnit> getSkuQuantityUnitsFromDto(Set<SkuQuantityUnitDto> skuQuantityUnitDtos) {
        Set<SkuQuantityUnit> skuQuantityUnits = new HashSet<>();
        if(skuQuantityUnitDtos == null) {
            return new HashSet<>();
        }
        for(SkuQuantityUnitDto skuQuantityUnitDto : skuQuantityUnitDtos) {
            skuQuantityUnits.add(new SkuQuantityUnit(skuQuantityUnitDto.getName(), skuQuantityUnitDto.getPrice(), skuQuantityUnitDto.getDimension()));
        }
        return skuQuantityUnits;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<SkuQuantityUnit> getSkuQuantityUnits() {
        return skuQuantityUnits;
    }

    public void setSkuQuantityUnits(Set<SkuQuantityUnit> skuQuantityUnits) {
        this.skuQuantityUnits = skuQuantityUnits;
    }

    public SkuQuantityUnit getSkuDefaultUnit() {
        return skuDefaultUnit;
    }

    public void setSkuDefaultUnit(SkuQuantityUnit skuDefaultUnit) {
        this.skuDefaultUnit = skuDefaultUnit;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", skuQuantityUnits=" + skuQuantityUnits +
                ", skuDefaultUnit=" + skuDefaultUnit +
                '}';
    }
}
