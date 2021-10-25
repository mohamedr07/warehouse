package com.example.demo.advice.model;

import com.example.demo.Inventory.model.Inventory;
import com.example.demo.sku.model.Sku;
import com.example.demo.sku.model.SkuQuantityUnit;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = AdviceLine.TABLE_NAME)
public class AdviceLine {

    final static String TABLE_NAME = "ADVICE_LINE";

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

    @OneToOne()
    @JoinColumn(name = "INVENTORY_ID")
    private Inventory inventory;

    public AdviceLine() {
    }

    public AdviceLine(Sku sku, SkuQuantityUnit skuQuantityUnit, Long quantity, LocalDate bestSellBefore) {
        this.sku = sku;
        this.skuQuantityUnit = skuQuantityUnit;
        this.quantity = quantity;
        this.bestSellBefore = bestSellBefore;
    }

    public AdviceLine(Long id, Sku sku, SkuQuantityUnit skuQuantityUnit, Long quantity, LocalDate bestSellBefore, Inventory inventory) {
        this.id = id;
        this.sku = sku;
        this.skuQuantityUnit = skuQuantityUnit;
        this.quantity = quantity;
        this.bestSellBefore = bestSellBefore;
        this.inventory = inventory;
    }

    public LocalDate getBestSellBefore() {
        return bestSellBefore;
    }

    public void setBestSellBefore(LocalDate bestSellBefore) {
        this.bestSellBefore = bestSellBefore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sku getSku() {
        return sku;
    }

    public void setSku(Sku sku) {
        this.sku = sku;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public SkuQuantityUnit getSkuQuantityUnit() {
        return skuQuantityUnit;
    }

    public void setSkuQuantityUnit(SkuQuantityUnit skuQuantityUnit) {
        this.skuQuantityUnit = skuQuantityUnit;
    }

    @Override
    public String toString() {
        return "AdviceLine{" +
                "id=" + id +
                ", sku=" + (sku != null ? sku.getId() : null) +
                ", skuQuantityUnit=" + (skuQuantityUnit != null ? skuQuantityUnit.getId() : null) +
                ", quantity=" + quantity +
                ", bestSellBefore=" + bestSellBefore +
                ", inventory=" + inventory +
                '}';
    }
}
