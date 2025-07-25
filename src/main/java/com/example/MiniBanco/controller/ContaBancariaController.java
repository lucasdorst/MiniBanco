package com.example.MiniBanco.controller;

import com.example.MiniBanco.model.ContaBancaria;
import com.example.MiniBanco.repository.ContaBancariaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/conta")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @PostMapping
    public BigDecimal depositar(@RequestBody ContaBancaria saldo){
        return contaBancariaRepository.save(saldo).getSaldo();
    }
}
