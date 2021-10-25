package com.example.demo.Inventory.controller;

import com.example.demo.Inventory.model.Inventory;
import com.example.demo.Inventory.service.InventoryService;
import com.example.demo.utility.dto.PageableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @PostMapping(path = "/add/{adviceId}")
    public ResponseEntity<Inventory> addAdviceToInventory(@PathVariable("adviceId") Long adviceId) {
        try {
            inventoryService.addAdviceToInventory(adviceId);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/subtract/{orderId}")
    public ResponseEntity<Inventory> subtractOrderFromInventory(@PathVariable("orderId") Long orderId) {
        try {
            inventoryService.subtractOrderFromInventory(orderId);
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping()
    public ResponseEntity<List<Inventory>> getInventories(@RequestBody PageableDto pageableDto) {
        List<Inventory> inventories = inventoryService.getInventories(pageableDto);
        if (inventories.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(inventories, HttpStatus.OK);
    }
}
