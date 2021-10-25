package com.example.demo.account.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SupplierDto {

    @NotEmpty
    @Size(min = 4)
    private String fullName;

    @Email
    private String email;

    @NotEmpty
    @Size(min = 8)
    private String password;

    @NotEmpty
    @Size(min = 11)
    private String phoneNumber;

    @NotEmpty
    private String warehouseLocation;

    public SupplierDto() {
    }

    public SupplierDto(String fullName, String email, String password, String phoneNumber, String warehouseLocation) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.warehouseLocation = warehouseLocation;
    }

    public SupplierDto(String fullName, String email, String phoneNumber, String warehouseLocation) {
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.warehouseLocation = warehouseLocation;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    @Override
    public String toString() {
        return "SupplierDto{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", warehouseLocation='" + warehouseLocation + '\'' +
                '}';
    }
}
