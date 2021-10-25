package com.example.demo.configuration.controller;

import com.example.demo.account.dto.CustomerDto;
import com.example.demo.configuration.dto.ConfigurationDto;
import com.example.demo.configuration.model.Configuration;
import com.example.demo.configuration.service.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @PostMapping()
    public ResponseEntity<ConfigurationDto> addConfiguration(@RequestBody @Valid ConfigurationDto configurationDto) {
        try {
            return new ResponseEntity<>(configurationService.addConfiguration(configurationDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("{name}")
    public ResponseEntity<ConfigurationDto> updateConfiguration(@PathVariable String name, @RequestBody ConfigurationDto configurationDto) {
        configurationDto = configurationService.updateConfiguration(name, configurationDto);
        if(configurationDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(configurationDto, HttpStatus.OK);
    }

    @PostMapping("/set/default")
    public ResponseEntity setDefaults() {
        try {
            configurationService.setDefaults();
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
