package com.example.MiniBanco.service;

//parte logica do programa (regra de negocio)

import com.example.MiniBanco.model.Cliente;
import com.example.MiniBanco.model.ContaBancaria;
import com.example.MiniBanco.model.Transacao;
import com.example.MiniBanco.model.enums.TipoConta;
import com.example.MiniBanco.repository.ClienteRepository;
import com.example.MiniBanco.repository.ContaBancariaRepository;
import com.example.MiniBanco.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

//parte logica do sistema

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TransacaoRepository transacaoRepository;

    public ContaBancaria criar (Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId).
                orElseThrow(()-> new RuntimeException("Cliente não encontrado"));

        //vai criar um objeto contabancaria, assim definindo como deve ser ao criar uma nova
        ContaBancaria conta = new ContaBancaria();
        conta.setCliente(cliente); //o cliente dono da conta
        conta.setSaldo(BigDecimal.ZERO); // o valor inicial da conta 00,00
        conta.setAgencia("0001");
        conta.setNumeroConta(gerarNumeroConta()); // vai gerar automaticamente o numero da conta com o metodo criado embaixo
        conta.setTipoConta(TipoConta.CORRENTE);

        return contaBancariaRepository.save(conta);
    }

    private String gerarNumeroConta() {
        // Exemplo: gera número único com os 6 primeiros dígitos do UUID
        String numero;
        do{
            numero = UUID.randomUUID().toString().substring(0, 6);
        } while (contaBancariaRepository.existsByNumeroConta(numero));

        return numero;
    }

    public ContaBancaria depositar (Long contaId, BigDecimal valor){
        ContaBancaria conta = contaBancariaRepository.findById(contaId)
                .orElseThrow(()-> new RuntimeException("Conta não encontrada"));

        if (valor == null || valor.compareTo(BigDecimal.ZERO)<=0){
            throw new RuntimeException("Valor do depósito deve ser positivo");
        }
        conta.setSaldo(conta.getSaldo().add(valor));

        registrarTransacao(conta, valor, "Depositar", "Deposito com sucesso");
        return contaBancariaRepository.save(conta);

    }

    public ContaBancaria sacar (Long contaId, BigDecimal valor){
        ContaBancaria conta = contaBancariaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (conta.getSaldo().compareTo(valor)<=0){
            throw new RuntimeException("valor insuficiente para saque");
        }
        if (valor.compareTo(BigDecimal.ZERO)<=0){
            throw new RuntimeException("Valor do depósito deve ser positivo");
        }

        conta.setSaldo(conta.getSaldo().subtract(valor));
        registrarTransacao(conta, valor, "saque", "Saque com sucesso");
        return contaBancariaRepository.save(conta);
    }

    public BigDecimal transferencia(Long origemId, Long destinoId, BigDecimal valor){
        ContaBancaria contaOrigem = contaBancariaRepository.findById(origemId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        ContaBancaria contaDestino = contaBancariaRepository.findById(destinoId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        if (contaOrigem.getSaldo().compareTo(valor)<0){
            throw new RuntimeException("Saldo insuficiente");
        }

        if (valor.compareTo(BigDecimal.ZERO)<=0){
            throw new RuntimeException("Valor do depósito deve ser positivo");
        }

        contaOrigem.setSaldo(contaOrigem.getSaldo().subtract(valor));
        registrarTransacao(contaOrigem,valor, "Transferido", "Transferido com sucesso");

        contaDestino.setSaldo(contaDestino.getSaldo().add(valor));
        registrarTransacao(contaDestino, valor, "Recebido", "Recebido com sucesso");

        contaBancariaRepository.save(contaOrigem);
        contaBancariaRepository.save(contaDestino);

        return contaOrigem.getSaldo();
    }

    public void registrarTransacao (ContaBancaria conta, BigDecimal valor, String tipo, String descricao){
        Transacao transacao = new Transacao(conta, valor, tipo, descricao);
        transacaoRepository.save(transacao);
    }
}
