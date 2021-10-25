package com.example.demo.loadunit.controller;

import com.example.demo.loadunit.dto.LoadUnitDto;
import com.example.demo.loadunit.dto.LoadUnitTypeDto;
import com.example.demo.loadunit.model.LoadUnit;
import com.example.demo.loadunit.repository.LoadUnitRepository;
import com.example.demo.loadunit.service.LoadUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/loadunit")
public class LoadUnitController {

    @Autowired
    private LoadUnitService loadUnitService;

    @PostMapping("/add")
    public ResponseEntity<LoadUnitDto> addLoadUnit(@RequestBody @Valid LoadUnitTypeDto loadUnitTypeDto) {
        try {
            return new ResponseEntity<>(loadUnitService.addLoadUnit(loadUnitTypeDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addlist")
    public ResponseEntity<List<LoadUnitDto>> addLoadUnits(@RequestBody @Valid LoadUnitTypeDto loadUnitTypeDto) {
        try {
            return new ResponseEntity<>(loadUnitService.addLoadUnits(loadUnitTypeDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
