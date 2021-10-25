package com.example.demo.order.model;

import com.example.demo.utility.enums.OrderAndAdviceStatus;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = Order.TABLE_NAME)
public class Order {

    final static String TABLE_NAME = "\"ORDER\"";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private OrderAndAdviceStatus status;

    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ORDER_ID")
    private List<OrderLine> orderLines;

    public Order() {
        orderLines = new ArrayList<>();
    }

    public Order(LocalDate date, OrderAndAdviceStatus status, Long customerId, List<OrderLine> orderLines) {
        this();
        this.date = date;
        this.status = status;
        this.customerId = customerId;
        this.orderLines = orderLines;
    }

    public Order(Long id, LocalDate date, OrderAndAdviceStatus status, Long customerId, List<OrderLine> orderLines) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.customerId = customerId;
        this.orderLines = orderLines;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public OrderAndAdviceStatus getStatus() {
        return status;
    }

    public void setStatus(OrderAndAdviceStatus status) {
        this.status = status;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", customerId=" + customerId +
                ", orderLines=" + orderLines +
                '}';
    }
}
