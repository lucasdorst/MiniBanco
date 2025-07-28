package com.example.MiniBanco.controller;

import com.example.MiniBanco.model.ContaBancaria;
import com.example.MiniBanco.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//lida com a requisicioes http, vai se comunicar com a API
@RestController
@RequestMapping("/contas")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @PostMapping("/criar/{clienteId}")
    public ContaBancaria criar(@PathVariable Long clienteId){
        return contaBancariaService.criar(clienteId);
    }
}
