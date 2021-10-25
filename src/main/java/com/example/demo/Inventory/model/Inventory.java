package com.example.demo.Inventory.model;

import com.example.demo.sku.model.Sku;
import com.example.demo.sku.model.SkuQuantityUnit;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = Inventory.TABLE_NAME)
public class Inventory {

    final static String TABLE_NAME = "INVENTORY";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "SKU_ID", nullable = false)
    private Sku sku;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "SKU_QUANTITY_UNIT_ID", nullable = false)
    private SkuQuantityUnit skuQuantityUnit;

    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;

    @Column(name = "BEST_SELL_BEFORE")
    private LocalDate bestSellBefore;

    public Inventory() {
    }

    public Inventory(Sku sku, SkuQuantityUnit skuQuantityUnit, Long quantity, LocalDate bestSellBefore) {
        this.sku = sku;
        this.skuQuantityUnit = skuQuantityUnit;
        this.quantity = quantity;
        this.bestSellBefore = bestSellBefore;
    }

    public Long getId() {
        return id;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
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

    public LocalDate getBestSellBefore() {
        return bestSellBefore;
    }

    public void setBestSellBefore(LocalDate bestSellBefore) {
        this.bestSellBefore = bestSellBefore;
    }

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", sku=" + sku +
                ", skuQuantityUnit=" + skuQuantityUnit +
                ", quantity=" + quantity +
                ", bestSellBefore=" + bestSellBefore +
                '}';
    }
}
