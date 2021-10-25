package com.example.demo.advice.model;

import com.example.demo.utility.enums.OrderAndAdviceStatus;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity(name = Advice.TABLE_NAME)
public class Advice {

    final static String TABLE_NAME = "ADVICE";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS", nullable = false)
    private OrderAndAdviceStatus status;

    @Column(name = "SUPPLIER_ID", nullable = false)
    private Long supplierId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ADVICE_ID")
    private List<AdviceLine> adviceLines;

    public Advice() {
        adviceLines =  new ArrayList<>();
    }

    public Advice(LocalDate date, OrderAndAdviceStatus status, Long supplierId, List<AdviceLine> adviceLines) {
        this();
        this.date = date;
        this.status = status;
        this.supplierId = supplierId;
        this.adviceLines = adviceLines;
    }

    public Advice(Long id, LocalDate date, OrderAndAdviceStatus status, Long supplierId, List<AdviceLine> adviceLines) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.supplierId = supplierId;
        this.adviceLines = adviceLines;
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

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public List<AdviceLine> getAdviceLines() {
        return adviceLines;
    }

    public void setAdviceLines(List<AdviceLine> adviceLines) {
        this.adviceLines = adviceLines;
    }

    @Override
    public String toString() {
        return "Advice{" +
                "id=" + id +
                ", date=" + date +
                ", status=" + status +
                ", supplierId=" + supplierId +
                ", adviceLines=" + adviceLines +
                '}';
    }
}
