package com.example.demo.role.service;

import com.example.demo.role.dto.RoleDto;
import com.example.demo.role.dto.SetPermissionDto;
import com.example.demo.role.model.Permission;
import com.example.demo.role.model.Role;
import com.example.demo.role.repository.PermissionRepository;
import com.example.demo.role.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    // ADD NEW ROLE
    public Role createRole(RoleDto roleDto) {
        Role role = roleRepository.findRoleByName(roleDto.getName());
        if(role != null) {
            throw new IllegalStateException("Role already exists");
        }
        role = new Role(roleDto.getName(), roleDto.getDescription());
        return roleRepository.save(role);
    }

    //GET ROLE
    public Optional<Role> getRole(Long roleId) {
        return roleRepository.findById(roleId);
    }

    // GET ROLES
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    // UPDATE ROLE
    @Transactional
    public Role updateRole(Long roleId, RoleDto roleDto) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if(roleOptional.isEmpty()) {
            throw new IllegalStateException("Role Does Not Exist");
        }
        Role role = roleOptional.get();
        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        return role;
    }

    // DELETE ROLE
    public void deleteRole(Long roleId) {
        boolean exists = roleRepository.existsById(roleId);
        if(!exists) {
            throw new IllegalStateException("Role with id " + roleId + " does not exists");
        }
        roleRepository.deleteById(roleId);
    }

    @Transactional
    public Role setPermissions(Long roleId, SetPermissionDto setPermissionDto) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if(roleOptional.isEmpty()){
            throw new IllegalStateException("Role Does Not Exist");
        }
        Role role = roleOptional.get();
        List<Permission> permissions = permissionRepository.findAllById(setPermissionDto.getIds());
        if(permissions.isEmpty()) {
            throw new IllegalStateException("Permissions Does Not Exist");
        }
        role.getPermissions().clear();
        role.getPermissions().addAll(permissions);
        return role;
    }
}
