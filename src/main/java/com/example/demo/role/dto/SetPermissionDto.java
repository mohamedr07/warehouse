package com.example.demo.role.dto;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

public class SetPermissionDto {
    @NotEmpty
    private Set<Long> ids;

    public SetPermissionDto() {
    }

    public SetPermissionDto(Set<Long> ids) {
        this.ids = ids;
    }

    public Set<Long> getIds() {
        return ids;
    }

    public void setIds(Set<Long> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "SetPermissionDto{" +
                "ids=" + ids +
                '}';
    }
}
