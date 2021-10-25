package com.example.demo.account.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UpdateAccountDto {

    private Long roleId;

    public UpdateAccountDto() {
    }

    public UpdateAccountDto(Long roleId) {
        this.roleId = roleId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UpdateAccountDto{" +
                "roleId=" + roleId +
                '}';
    }
}
