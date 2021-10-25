package com.example.demo.role.controller;

import com.example.demo.role.dto.RoleDto;
import com.example.demo.role.dto.SetPermissionDto;
import com.example.demo.role.model.Role;
import com.example.demo.role.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    // ADD ROLE
    @PostMapping()
    public ResponseEntity<Role> createRole(@RequestBody @Valid RoleDto roleDto) {
        try {
            return new ResponseEntity<>(roleService.createRole(roleDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  GET ALL ROLES
    @GetMapping()
    public ResponseEntity<List<Role>> getRoles() {
        List<Role> roles = roleService.getRoles();
        if (roles.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    //  GET ROLE
    @GetMapping(path = "{roleId}")
    public ResponseEntity<Role> getRole(@PathVariable("roleId") Long roleId) {
        Optional<Role> role= roleService.getRole(roleId);
        if (role.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role.get(), HttpStatus.OK);
    }

    //  UPDATE ROLE
    @PutMapping(path = "{roleId}")
    public ResponseEntity<Role> updateRole(@PathVariable("roleId") Long roleId,@RequestBody @Valid RoleDto roleDto) {
        Role role = roleService.updateRole(roleId, roleDto);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    //  DELETE ROLE
    @DeleteMapping(path = "{roleId}")
    public ResponseEntity<HttpStatus> deleteRole(@PathVariable("roleId") long roleId) {
        try {
            roleService.deleteRole(roleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/setpermissions/{roleId}")
    public ResponseEntity<Role> setPermissions(@PathVariable("roleId") Long roleId, @RequestBody @Valid SetPermissionDto setPermissionDto) {
        Role role = roleService.setPermissions(roleId, setPermissionDto);
        if (role == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
