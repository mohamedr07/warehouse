package com.example.demo.sku.model;

import com.example.demo.sku.dto.SkuQuantityUnitDto;
import com.example.demo.utility.model.Dimension;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = SkuQuantityUnit.TABLE_NAME)
public class SkuQuantityUnit {

    final static String TABLE_NAME = "SKU_QUANTITY_UNIT";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false, length = 150)
    private String name;

    @Column(name = "PRICE", nullable = false)
    private Double price;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SKU_ID")
    private Sku sku;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIMENSION")
    private Dimension dimension;

    public SkuQuantityUnit() {
    }

    public SkuQuantityUnit(String name, Double price, Sku sku, Dimension dimension) {
        this.name = name;
        this.price = price;
        this.sku = sku;
        this.dimension = dimension;
    }

    public SkuQuantityUnit(String name, Double price, Dimension dimension) {
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

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SkuQuantityUnit that = (SkuQuantityUnit) o;
        return name.equals(that.name) && price.equals(that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public String toString() {
        return "SkuQuantityUnit{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", sku=" + (sku != null ? sku.getId() : null) +
                ", dimension=" + dimension +
                '}';
    }
}
