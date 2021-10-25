package com.example.demo.order.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class OrderLineDto {

    @NotEmpty
    private Long skuQuantityUnitId;

    @NotEmpty
    private Long quantity;

    private LocalDate bestSellBefore;

    public OrderLineDto() {
    }

    public OrderLineDto(Long skuQuantityUnitId, Long quantity, LocalDate bestSellBefore) {
        this.skuQuantityUnitId = skuQuantityUnitId;
        this.quantity = quantity;
        this.bestSellBefore = bestSellBefore;
    }

    public Long getSkuQuantityUnitId() {
        return skuQuantityUnitId;
    }

    public void setSkuQuantityUnitId(Long skuQuantityUnitId) {
        this.skuQuantityUnitId = skuQuantityUnitId;
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
        return "OrderLineDto{" +
                "skuQuantityUnitId=" + skuQuantityUnitId +
                ", quantity=" + quantity +
                ", bestSellBefore=" + bestSellBefore +
                '}';
    }
}
