package com.example.demo.configuration.dto;

import com.example.demo.configuration.model.Configuration;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class ConfigurationDto {

    private String name;

    @NotBlank
    private String value;

    public ConfigurationDto() {
    }

    public ConfigurationDto(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public ConfigurationDto(Configuration configuration) {
        this.name = configuration.getName();
        this.value = configuration.getValue();
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ConfigurationDto{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
