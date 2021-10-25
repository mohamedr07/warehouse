package com.example.demo.loadunit.model;

import com.example.demo.loadunit.dto.LoadUnitTypeDto;
import com.example.demo.utility.model.Dimension;

import javax.persistence.*;

@Entity
@Table(name = LoadUnitType.TABLE_NAME)
public class LoadUnitType {

    final static String TABLE_NAME = "LOAD_UNIT_TYPE";

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NAME", unique = true, nullable = false, length = 150)
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DIMENSION")
    private Dimension dimension;

    public LoadUnitType() {
    }

    public LoadUnitType(String name, Dimension dimension) {
        this.name = name;
        this.dimension = dimension;
    }

    public LoadUnitType(LoadUnitTypeDto loadUnitTypeDto) {
        this.name = loadUnitTypeDto.getName();
        this.dimension = loadUnitTypeDto.getDimension();
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Dimension getDimension() {
        return dimension;
    }

    @Override
    public String toString() {
        return "LoadUnitType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dimension=" + dimension +
                '}';
    }
}
