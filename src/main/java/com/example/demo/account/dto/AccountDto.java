package com.example.demo.account.dto;

import com.example.demo.account.model.Account;
import com.example.demo.account.model.Customer;
import com.example.demo.account.model.Supplier;
import com.example.demo.role.model.Role;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AccountDto {

    private Long id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String address;
    private String warehouseLocation;
    private Role role;

    public AccountDto() {
    }

    public AccountDto(Long id, String fullName, String email, String phoneNumber, String address, String warehouseLocation) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.warehouseLocation = warehouseLocation;
    }

    public AccountDto(Account account) {
        Customer customer = account instanceof Customer ? ((Customer) account) : null;
        Supplier supplier = account instanceof Supplier ? ((Supplier) account) : null;
        this.id = account.getId();
        this.fullName = account.getFullName();
        this.email = account.getEmail();
        this.phoneNumber = account.getPhoneNumber();
        this.role = account.getRole();
        if(customer != null) {
            this.address = customer.getAddress();
        }
        if(supplier != null) {
            this.warehouseLocation = supplier.getWarehouseLocation();
        }
    }


    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public String getWarehouseLocation() {
        return warehouseLocation;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setWarehouseLocation(String warehouseLocation) {
        this.warehouseLocation = warehouseLocation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "AccountDto{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", warehouseLocation='" + warehouseLocation + '\'' +
                ", role=" + role +
                '}';
    }
}
