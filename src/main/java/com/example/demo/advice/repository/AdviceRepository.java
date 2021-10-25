package com.example.demo.advice.repository;

import com.example.demo.advice.model.Advice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdviceRepository extends JpaRepository<Advice, Long> {
}
