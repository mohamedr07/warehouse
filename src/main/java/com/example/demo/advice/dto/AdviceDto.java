package com.example.demo.advice.dto;

import java.util.ArrayList;
import java.util.List;

public class AdviceDto {

    private List<AdviceLineDto> adviceLineDtos;

    public AdviceDto() {
        adviceLineDtos = new ArrayList<>();
    }

    public AdviceDto(List<AdviceLineDto> adviceLineDtos) {
        this.adviceLineDtos = adviceLineDtos;
    }

    public List<AdviceLineDto> getAdviceLineDtos() {
        return adviceLineDtos;
    }

    public void setAdviceLineDtos(List<AdviceLineDto> adviceLineDtos) {
        this.adviceLineDtos = adviceLineDtos;
    }

    @Override
    public String toString() {
        return "AdviceDto{" +
                "adviceLineDtos=" + adviceLineDtos +
                '}';
    }
}
