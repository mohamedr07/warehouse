package com.example.demo.Inventory.service;

import com.example.demo.advice.model.Advice;
import com.example.demo.advice.model.AdviceLine;
import com.example.demo.configuration.service.ConfigurationService;
import com.example.demo.loadunit.service.LoadUnitService;
import com.example.demo.utility.dto.PageableDto;
import com.example.demo.utility.enums.OrderAndAdviceStatus;
import com.example.demo.Inventory.model.*;
import com.example.demo.advice.repository.AdviceRepository;
import com.example.demo.Inventory.repository.InventoryRepository;
import com.example.demo.order.model.Order;
import com.example.demo.order.model.OrderLine;
import com.example.demo.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.demo.configuration.constant.ConfigurationConstant.inventoryToleranceKey;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private AdviceRepository adviceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private LoadUnitService loadUnitService;

    @Autowired
    private ConfigurationService configurationService;

    @Transactional
    public void addAdviceToInventory(Long adviceId) {
        Optional<Advice> adviceOptional = adviceRepository.findById(adviceId);
        if(adviceOptional.isEmpty()){
            throw new IllegalStateException("Advice Does Not Exist");
        }
        Advice advice = adviceOptional.get();
        List<AdviceLine> adviceLines = advice.getAdviceLines();
        Optional<Inventory> inventoryOptional;
        Inventory inventory;
        for(AdviceLine adviceLine : adviceLines) {
            loadUnitService.addSkus(adviceLine.getSkuQuantityUnit(), adviceLine.getQuantity());
            if(adviceLine.getBestSellBefore() == null) {
                inventoryOptional = inventoryRepository.findBySkuAndSkuQuantityUnit(adviceLine.getSku(), adviceLine.getSkuQuantityUnit());
                if(inventoryOptional.isPresent()) {
                    inventory = inventoryOptional.get();
                    inventory.setQuantity(inventory.getQuantity() + adviceLine.getQuantity());
                    adviceLine.setInventory(inventory);
                    continue;
                }
            }
            inventory = inventoryRepository.save(new Inventory(adviceLine.getSku(), adviceLine.getSkuQuantityUnit(), adviceLine.getQuantity(), adviceLine.getBestSellBefore()));
            adviceLine.setInventory(inventory);
        }
        advice.setStatus(OrderAndAdviceStatus.FINISHED);
    }

    @Transactional
    public void subtractOrderFromInventory(Long orderId) {
        Optional<Order> orderOptional = orderRepository.findById(orderId);
        if(orderOptional.isEmpty()) {
            throw new IllegalStateException("Order Does Not Exist");
        }
        Order order = orderOptional.get();
        List<OrderLine> orderLines = order.getOrderLines();
        Optional<Inventory> inventoryOptional;
        Inventory inventory;
        String inventoryTolerance = configurationService.findConfigurationByName(inventoryToleranceKey);
        for(OrderLine orderLine : orderLines){
            inventoryOptional = inventoryRepository.findBySkuAndSkuQuantityUnit(orderLine.getSku(), orderLine.getSkuQuantityUnit());
            if(inventoryOptional.isEmpty()) {
                throw new IllegalStateException("Sku Does Not Exist");
            }

            inventory = inventoryOptional.get();
            //
            if(orderLine.getQuantity() > inventory.getQuantity() && Objects.equals(inventoryTolerance, "false")) {
                throw new IllegalStateException("Quantity Error");
            }
            //
            if(orderLine.getQuantity() > inventory.getQuantity()) {
                loadUnitService.getSkus(orderLine.getSkuQuantityUnit(), inventory.getQuantity());
                inventory.setQuantity(0L);
                continue;
            }
            //
            inventory.setQuantity(inventory.getQuantity() - orderLine.getQuantity());
            loadUnitService.getSkus(orderLine.getSkuQuantityUnit(), orderLine.getQuantity());
        }
        order.setStatus(OrderAndAdviceStatus.FINISHED);
    }

    public List<Inventory> getInventories(PageableDto pageableDto) {

        Page<Inventory> inventoryPage = inventoryRepository.findAll(PageRequest.of(pageableDto.getPageNumber(), pageableDto.getNumberOfInstances()));
        List<Inventory> inventories = new ArrayList<>();
        inventoryPage.forEach(inventories::add);
        return inventories;
    }
}
