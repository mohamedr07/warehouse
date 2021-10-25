package com.example.demo.account.model;

import com.example.demo.order.model.Order;
import com.example.demo.role.model.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = Customer.TABLE_NAME)
public class Customer extends Account {

    final static String TABLE_NAME = "CUSTOMER";

    @Column(name = "ADDRESS")
    private String address;

    public Customer() {
    }

    public Customer(String fullName, String email, String hashedPassword, String phoneNumber, String address) {
        super(fullName, email, hashedPassword, phoneNumber);
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
        return "Customer{" +
                "address='" + address + '\'' +
                '}';
    }
}
