package com.example.demo.role.service;

import com.example.demo.role.dto.PermissionDto;
import com.example.demo.role.model.Permission;
import com.example.demo.role.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    // ADD NEW PERMISSION
    public Permission createPermission(PermissionDto permissionDto) {
        Optional<Permission> permissionOptional = permissionRepository.findPermissionByName(permissionDto.getName());
        if(permissionOptional.isPresent()) {
            throw new IllegalStateException("Permission already exists");
        }
        Permission permission = new Permission(permissionDto.getName(), permissionDto.getDescription());
        return permissionRepository.save(permission);
    }

    //GET A PERMISSION
    public Optional<Permission> getPermission(Long permissionId) {
        return permissionRepository.findById(permissionId);
    }

    // GET PERMISSIONS
    public List<Permission> getPermissions() {
        return permissionRepository.findAll();
    }

    // UPDATE PERMISSION
    @Transactional
    public Permission updatePermission(Long permissionId, PermissionDto permissionDto) {
        Optional<Permission> permissionOptional = permissionRepository.findById(permissionId);
        if(permissionOptional.isEmpty()) {
            throw new IllegalStateException("Permission Does Not Exist");
        }
        Permission permission = permissionOptional.get();
        permission.setName(permissionDto.getName());
        permission.setDescription(permissionDto.getDescription());
        return permission;
    }

    // DELETE PERMISSION
    public void deletePermission(Long permissionId) {
        boolean exists = permissionRepository.existsById(permissionId);
        if(!exists) {
            throw new IllegalStateException("Permission with id " + permissionId + " does not exists");
        }
        permissionRepository.deleteById(permissionId);
    }

}
