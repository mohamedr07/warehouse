package com.example.demo.configuration.constant;

import com.example.demo.configuration.model.Configuration;
import com.example.demo.role.dto.RoleDto;
import com.example.demo.sku.dto.SkuDto;
import com.example.demo.sku.dto.SkuQuantityUnitDto;
import com.example.demo.utility.model.Dimension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ConfigurationConstant {
    public final static String inventoryToleranceKey = "tolerance";
    public final static String inventoryToleranceValue = "true";
    public final static String adminFullName = "mohamed rashad";
    public final static String adminEmail = "admin@gmail.com";
    public final static String adminPassword = "123123123";
    public final static String adminPhoneNumber = "01112228547";
    public final static String adminAddress = "12, blaaaa, bllaaaa";
    public final static Long roleId = 1L;
    public final static Long adminId = 1L;
    public final static String roleName = "admin";
    public final static String[] permissionsNames = {"ALL-AUTH", "ALL-GET", "ALL-POST", "ALL-EDIT"};
    public final static String defaultLoadUnitName = "Box";
    public final static Dimension defaultLoadUnitDimensions = new Dimension(10, 10, 10);
    public final static Long loadUnitQuantity = 100L;


}
