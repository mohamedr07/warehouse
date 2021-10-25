package com.example.demo.account.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateSupplierDto {
    @NotEmpty
    @Size(min = 8)
    private String warehouseLocation;

    public UpdateSupplierDto() {
    }

    public UpdateSupplierDto(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    @Override
    public String toString() {
        return "UpdateSupplierDto{" +
                "warehouseLocation='" + warehouseLocation + '\'' +
                '}';
    }
}
