package com.example.demo.advice.service;

import com.example.demo.account.model.Supplier;
import com.example.demo.account.repository.SupplierRepository;
import com.example.demo.advice.dto.AdviceDto;
import com.example.demo.advice.dto.AdviceLineDto;
import com.example.demo.advice.model.Advice;
import com.example.demo.advice.model.AdviceLine;
import com.example.demo.advice.repository.AdviceRepository;
import com.example.demo.utility.dto.PageableDto;
import com.example.demo.utility.enums.OrderAndAdviceStatus;
import com.example.demo.sku.model.SkuQuantityUnit;
import com.example.demo.sku.repository.SkuQuantityUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdviceService {

    @Autowired
    private AdviceRepository adviceRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @Autowired
    private SkuQuantityUnitRepository skuQuantityUnitRepository;

    @Transactional
    public Advice createAdvice(Long supplierId, AdviceDto adviceDto) {
        Optional<Supplier> supplierOptional = supplierRepository.findById(supplierId);
        if(supplierOptional.isEmpty()) {
            throw new IllegalStateException("Supplier Does not Exist");
        }
        List<AdviceLine> adviceLines = new ArrayList<>();
        Optional<SkuQuantityUnit> skuQuantityUnitOptional;
        SkuQuantityUnit skuQuantityUnit;
        for(AdviceLineDto adviceLineDto : adviceDto.getAdviceLineDtos()) {
            skuQuantityUnitOptional = skuQuantityUnitRepository.findById(adviceLineDto.getSkuQuantityUnitId());
            if(skuQuantityUnitOptional.isEmpty()) {
                throw new IllegalStateException("Sku Does not Exist");
            }
            skuQuantityUnit = skuQuantityUnitOptional.get();
            adviceLines.add(new AdviceLine(skuQuantityUnit.getSku(), skuQuantityUnit, adviceLineDto.getQuantity(), adviceLineDto.getBestSellBefore()));
        }
        Advice advice = new Advice(LocalDate.now(), OrderAndAdviceStatus.CREATED, supplierId, adviceLines);
        advice = adviceRepository.save(advice);

        return advice;
    }

    public Optional<Advice> getAdvice(Long adviceId) {
        return adviceRepository.findById(adviceId);
    }

    public List<Advice> getAdvices(PageableDto pageableDto) {
        Page<Advice> advicePage = adviceRepository.findAll(PageRequest.of(pageableDto.getPageNumber(), pageableDto.getNumberOfInstances()));
        List<Advice> advices = new ArrayList<>();
        advicePage.forEach(advices::add);
        return advices;
    }

    public void deleteAdvice(Long adviceId) {
        boolean exists = adviceRepository.existsById(adviceId);
        if(!exists) {
            throw new IllegalStateException("Advice with id " + adviceId + " does not exists");
        }
        adviceRepository.deleteById(adviceId);
    }

}
