package com.example.demo.advice.controller;

import com.example.demo.advice.dto.AdviceDto;
import com.example.demo.advice.model.Advice;
import com.example.demo.advice.service.AdviceService;
import com.example.demo.utility.dto.PageableDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/advice")
public class AdviceController {

    @Autowired
    private AdviceService adviceService;

    // ADD ADVICE
    @PostMapping("/{supplierId}")
    public ResponseEntity<Advice> createAdvice(@PathVariable("supplierId") Long supplierId, @RequestBody @Valid AdviceDto adviceDto) {
        try {
            return new ResponseEntity<>(adviceService.createAdvice(supplierId, adviceDto), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //  GET ALL ADVICES
    @GetMapping()
    public ResponseEntity<List<Advice>> getAdvices(@RequestBody PageableDto pageableDto) {
        List<Advice> advices = adviceService.getAdvices(pageableDto);
        if (advices.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(advices, HttpStatus.OK);
    }

    //  GET ADVICE
    @GetMapping(path = "{adviceId}")
    public ResponseEntity<Advice> getAdvice(@PathVariable("adviceId") Long adviceId) {
        Optional<Advice> advice= adviceService.getAdvice(adviceId);
        if (advice.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(advice.get(), HttpStatus.OK);
    }

    //  DELETE ADVICE
    @DeleteMapping(path = "{adviceId}")
    public ResponseEntity<HttpStatus> deleteAdvice(@PathVariable("adviceId") long adviceId) {
        try {
            adviceService.deleteAdvice(adviceId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
