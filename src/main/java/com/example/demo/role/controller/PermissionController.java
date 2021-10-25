package com.example.demo.role.controller;

import com.example.demo.role.dto.PermissionDto;
import com.example.demo.role.model.Permission;
import com.example.demo.role.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    // ADD PERMISSION
    @PostMapping()
    public ResponseEntity<Permission> createPermission(@RequestBody @Valid PermissionDto permissionDto) {
        try {
            return new ResponseEntity<>(permissionService.createPermission(permissionDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  GET ALL PERMISSIONS
    @GetMapping()
    public ResponseEntity<List<Permission>> getPermissions() {
        List<Permission> permissions = permissionService.getPermissions();
        if (permissions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    //  GET PERMISSION
    @GetMapping(path = "{permissionId}")
    public ResponseEntity<Permission> getPermission(@PathVariable("permissionId") Long permissionId) {
        Optional<Permission> permissionOptional= permissionService.getPermission(permissionId);
        if (permissionOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(permissionOptional.get(), HttpStatus.OK);

    }

    //  UPDATE PERMISSION
    @PutMapping(path = "{permissionId}")
    public ResponseEntity<Permission> updatePermission(@PathVariable("permissionId") Long permissionId, @RequestBody @Valid PermissionDto permissionDto) {
        Permission permission = permissionService.updatePermission(permissionId, permissionDto);
        if (permission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(permission, HttpStatus.OK);
    }

    //  DELETE PERMISSION
    @DeleteMapping(path = "{permissionId}")
    public ResponseEntity<HttpStatus> deletePermission(@PathVariable("permissionId") long permissionId) {
        try {
            permissionService.deletePermission(permissionId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
