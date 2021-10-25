package com.example.demo.utility.dto;

import javax.validation.constraints.NotEmpty;

public class PageableDto {
    @NotEmpty
    private Integer pageNumber;

    @NotEmpty
    private Integer numberOfInstances;

    public PageableDto() {
    }

    public PageableDto(Integer pageNumber, Integer numberOfInstances) {
        this.pageNumber = pageNumber;
        this.numberOfInstances = numberOfInstances;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(Integer numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    @Override
    public String toString() {
        return "PageableDto{" +
                "pageNumber=" + pageNumber +
                ", numberOfInstances=" + numberOfInstances +
                '}';
    }
}
