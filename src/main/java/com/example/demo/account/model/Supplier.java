package com.example.demo.account.model;

import com.example.demo.advice.model.Advice;
import com.example.demo.role.model.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Supplier.TABLE_NAME)
public class Supplier extends Account {

    final static String TABLE_NAME = "SUPPLIER";

    @Column(name = "WAREHOUSE_LOCATION")
    private String warehouseLocation;


    public Supplier() {
    }

    public Supplier(String fullName, String email, String hashedPassword, String phoneNumber, String warehouseLocation) {
        super(fullName, email, hashedPassword, phoneNumber);
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
        return "Supplier{" +
                "warehouseLocation='" + warehouseLocation + '\'' +
                '}';
    }
}
