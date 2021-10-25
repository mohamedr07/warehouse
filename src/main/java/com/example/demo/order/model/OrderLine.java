package com.example.demo.order.model;

import com.example.demo.Inventory.model.Inventory;
import com.example.demo.sku.model.Sku;
import com.example.demo.sku.model.SkuQuantityUnit;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity(name = OrderLine.TABLE_NAME)
public class OrderLine {

    final static String TABLE_NAME = "ORDER_LINE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "SKU_ID", nullable = false)
    private Sku sku;

    @ManyToOne()
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "SKU_QUANTITY_UNIT", nullable = false)
    private SkuQuantityUnit skuQuantityUnit;

    @Column(name = "QUANTITY", nullable = false)
    private Long quantity;


    public OrderLine() {
    }

    public OrderLine(Sku sku, SkuQuantityUnit skuQuantityUnit, Long quantity) {
        this.sku = sku;
        this.skuQuantityUnit = skuQuantityUnit;
        this.quantity = quantity;
    }

    public OrderLine(Long id, Sku sku, SkuQuantityUnit skuQuantityUnit, Long quantity) {
        this.id = id;
        this.sku = sku;
        this.skuQuantityUnit = skuQuantityUnit;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "OrderLine{" +
                "id=" + id +
                ", sku=" + (sku != null ? sku.getId() : null) +
                ", skuQuantityUnit=" + (skuQuantityUnit != null ? skuQuantityUnit.getId() : null) +
                ", quantity=" + quantity +
                '}';
    }
}
