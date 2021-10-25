package com.example.demo.configuration.model;

import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;

@Entity
@Table(name = Configuration.TABLE_NAME)
public class Configuration {

    static final String TABLE_NAME = "CONFIGURATION";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false)
    private String name;

    @Column(name = "VALUE", nullable = false)
    private String value;

    public Configuration() {
    }

    public Configuration(Long id, String name, String value) {
        this.id = id;
        this.name = name;
        this.value = value;
    }

    public Configuration(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Configuration{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
