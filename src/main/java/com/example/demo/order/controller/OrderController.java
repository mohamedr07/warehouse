package com.example.demo.order.controller;

import com.example.demo.utility.dto.PageableDto;
import com.example.demo.order.dto.OrderDto;
import com.example.demo.order.model.Order;
import com.example.demo.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // ADD ORDER
    @PostMapping("/{customerId}")
    public ResponseEntity<Order> createOrder(@PathVariable("customerId") Long customerId, @RequestBody @Valid OrderDto orderDto) {
        try {
            return new ResponseEntity<>(orderService.createOrder(customerId, orderDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  GET ALL ORDERS
    @GetMapping()
    @Secured("API_ORDER_ALL_ACTIONS")
    public ResponseEntity<List<Order>> getOrders(@RequestBody PageableDto pageableDto) {
        List<Order> orders = orderService.getOrders(pageableDto);
        if (orders.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    //  GET ORDER
    @GetMapping(path = "{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable("orderId") Long orderId) {
        Optional<Order> order= orderService.getOrder(orderId);
        if (order.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(order.get(), HttpStatus.OK);
    }

    //  DELETE ORDER
    @DeleteMapping(path = "{orderId}")
    public ResponseEntity<HttpStatus> deleteOrder(@PathVariable("orderId") long orderId) {
        try {
            orderService.deleteOrder(orderId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
