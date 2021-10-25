package com.example.demo.role.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PermissionDto {

    @NotEmpty
    @Size(min = 4)
    private String name;
    private String description;

    public PermissionDto() {
    }

    public PermissionDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "PermissionDto{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
