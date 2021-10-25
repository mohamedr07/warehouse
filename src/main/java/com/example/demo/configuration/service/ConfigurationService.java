package com.example.demo.configuration.service;

import com.example.demo.account.dto.CustomerDto;
import com.example.demo.account.dto.UpdateAccountDto;
import com.example.demo.account.service.AccountService;
import com.example.demo.account.service.CustomerService;
import com.example.demo.configuration.dto.ConfigurationDto;
import com.example.demo.configuration.model.Configuration;
import com.example.demo.configuration.repository.ConfigurationRepository;
import com.example.demo.loadunit.dto.LoadUnitTypeDto;
import com.example.demo.loadunit.service.LoadUnitService;
import com.example.demo.role.dto.PermissionDto;
import com.example.demo.role.dto.RoleDto;
import com.example.demo.role.dto.SetPermissionDto;
import com.example.demo.role.service.PermissionService;
import com.example.demo.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.example.demo.configuration.constant.ConfigurationConstant.*;

@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoadUnitService loadUnitService;

    public ConfigurationDto updateConfiguration(String name, ConfigurationDto configurationDto) {
        Configuration Configuration = configurationRepository.findByName(name);
        if(Configuration == null) {
            throw new IllegalStateException("configuration not found");
        }
        Configuration.setValue(configurationDto.getValue());
        return new ConfigurationDto(Configuration);
    }

    public ConfigurationDto addConfiguration(ConfigurationDto configurationDto) {
        Configuration configuration = configurationRepository.findByName(configurationDto.getName());
        if(configuration != null) {
            throw new IllegalStateException("configuration already exists");
        }
        configuration = new Configuration(configurationDto.getName(), configurationDto.getValue());
        configuration = configurationRepository.save(configuration);
        return new ConfigurationDto(configuration);
    }

    public String findConfigurationByName(String name) {
        return configurationRepository.findByName(name).getValue();
    }

    public void setDefaults() {
        configurationRepository.save(new Configuration(inventoryToleranceKey, inventoryToleranceValue));

        CustomerDto admin = new CustomerDto(adminFullName, adminEmail, adminPassword, adminPhoneNumber, adminAddress);
        RoleDto roleDto = new RoleDto(roleName, null);
        PermissionDto permissionDto;

        customerService.createCustomer(admin);
        roleService.createRole(roleDto);
        for(String permissionName : permissionsNames) {
            permissionDto = new PermissionDto(permissionName, null);
            permissionService.createPermission(permissionDto);
        }

        Set<Long> ids = new HashSet<>();
        ids.add(adminId);
        roleService.setPermissions(roleId, new SetPermissionDto(ids));
        accountService.setRole(adminId, new UpdateAccountDto(roleId));

        LoadUnitTypeDto loadUnitTypeDto = new LoadUnitTypeDto(defaultLoadUnitName, defaultLoadUnitDimensions, loadUnitQuantity);
        loadUnitService.addLoadUnits(loadUnitTypeDto);


    }
}
