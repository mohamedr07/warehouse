package com.example.demo.order.service;

import com.example.demo.account.model.Customer;
import com.example.demo.account.repository.CustomerRepository;
import com.example.demo.utility.dto.PageableDto;
import com.example.demo.order.dto.OrderDto;
import com.example.demo.order.dto.OrderLineDto;
import com.example.demo.order.model.Order;
import com.example.demo.order.model.OrderLine;
import com.example.demo.utility.enums.OrderAndAdviceStatus;
import com.example.demo.order.repository.OrderRepository;
import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.sku.repository.SkuQuantityUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SkuQuantityUnitRepository skuQuantityUnitRepository;

    @Transactional()
    public Order createOrder(Long customerId, OrderDto orderDto) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isEmpty()) {
            throw new IllegalStateException("Customer Does not Exist");
        }
        List<OrderLine> orderLines = new ArrayList<>();
        Optional<SkuQuantityUnit> skuQuantityUnitOptional;
        SkuQuantityUnit skuQuantityUnit;
        for(OrderLineDto orderLineDto : orderDto.getOrderLineDtos()) {
            skuQuantityUnitOptional = skuQuantityUnitRepository.findById(orderLineDto.getSkuQuantityUnitId());
            if(skuQuantityUnitOptional.isEmpty()) {
                throw new IllegalStateException("Sku Does not Exist");
            }
            skuQuantityUnit = skuQuantityUnitOptional.get();
            orderLines.add(new OrderLine(skuQuantityUnit.getSku(), skuQuantityUnit, orderLineDto.getQuantity()));
        }
        Order order = new Order(LocalDate.now(), OrderAndAdviceStatus.CREATED, customerId, orderLines);
        order = orderRepository.save(order);

        return order;
    }

    public Optional<Order> getOrder(Long orderId) {
        return orderRepository.findById(orderId);
    }

    public List<Order> getOrders(PageableDto pageableDto) {
        Page<Order> orderPage = orderRepository.findAll(PageRequest.of(pageableDto.getPageNumber(), pageableDto.getNumberOfInstances()));
        List<Order> orders = new ArrayList<>();
        orderPage.forEach(orders::add);
        return orders;
    }

    public void deleteOrder(Long orderId) {
        boolean exists = orderRepository.existsById(orderId);
        if(!exists) {
            throw new IllegalStateException("Order with id " + orderId + " does not exists");
        }
        orderRepository.deleteById(orderId);
    }
}
