package com.example.demo.account.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateCustomerDto {

    @NotEmpty
    @Size(min = 8)
    private String address;

    public UpdateCustomerDto() {
    }

    public UpdateCustomerDto(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UpdateCustomerDto{" +
                "address='" + address + '\'' +
                '}';
    }
}

