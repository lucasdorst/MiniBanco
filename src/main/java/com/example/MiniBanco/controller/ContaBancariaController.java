package com.example.MiniBanco.controller;

import com.example.MiniBanco.dto.TransacaoDTO;
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
        //@PathVariable inidica que o valor vai vir no URL
        return contaBancariaService.criar(clienteId); //chama o serviço para criar a conta
    }

    @PostMapping("/{contaId}/depositar")
    public ContaBancaria depositar(@PathVariable Long contaId, @RequestBody TransacaoDTO transacao){
        //@RequestBody significa que o valor vai vir no corpo da requisição (em json)
        return contaBancariaService.depositar(contaId,transacao.getValor());
    }
}
