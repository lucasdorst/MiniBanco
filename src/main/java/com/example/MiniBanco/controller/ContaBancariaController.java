package com.example.MiniBanco.controller;

import com.example.MiniBanco.dto.TransacaoDTO;
import com.example.MiniBanco.model.ContaBancaria;
import com.example.MiniBanco.model.Transacao;
import com.example.MiniBanco.repository.ContaBancariaRepository;
import com.example.MiniBanco.repository.TransacaoRepository;
import com.example.MiniBanco.service.ContaBancariaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


//lida com a requisicioes http, vai se comunicar com a API
@RestController
@RequestMapping("/contas")
public class ContaBancariaController {

    @Autowired
    private ContaBancariaService contaBancariaService;

    @Autowired
    private TransacaoRepository transacaoRepository;


    @PostMapping("/criar/{clienteId}")
    public ContaBancaria criar(@PathVariable Long clienteId){
        //@PathVariable inidica que o valor vai vir no URL
        return contaBancariaService.criar(clienteId); //chama o serviço para criar a conta
    }

    @PostMapping("/depositar/{contaId}")
    public ContaBancaria depositar(@PathVariable Long contaId, @RequestBody TransacaoDTO transacao){
        //@RequestBody significa que o valor vai vir no corpo da requisição (em json)
        return contaBancariaService.depositar(contaId,transacao.getValor());
    }

    @PostMapping("/sacar/{contaId}")
    public ContaBancaria sacar (@PathVariable Long contaId, @RequestBody TransacaoDTO transacao){
        return contaBancariaService.sacar(contaId,transacao.getValor());
    }

    @PostMapping("/transferencia")
    public String transferencia(@RequestBody TransacaoDTO dto){
        contaBancariaService.transferencia(dto.getOrigemId(), dto.getDestinoId(), dto.getValor());
        return ("Transferência realizada com sucesso");
    }

    @GetMapping("/registro/{contaId}")
    public List<Transacao> registroTransacao(@PathVariable Long contaId){
        return transacaoRepository.findByContaId(contaId);
    }
}
