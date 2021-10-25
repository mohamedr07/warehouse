package com.example.demo.order.dto;

import com.example.demo.advice.dto.AdviceLineDto;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private List<OrderLineDto> orderLineDtos;

    public OrderDto() {
        orderLineDtos = new ArrayList<>();
    }

    public OrderDto(List<OrderLineDto> orderLineDtos) {
        this.orderLineDtos = orderLineDtos;
    }

    public List<OrderLineDto> getOrderLineDtos() {
        return orderLineDtos;
    }

    public void setOrderLineDtos(List<OrderLineDto> orderLineDtos) {
        this.orderLineDtos = orderLineDtos;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "orderLineDtos=" + orderLineDtos +
                '}';
    }
}
