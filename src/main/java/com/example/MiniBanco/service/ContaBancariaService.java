package com.example.MiniBanco.service;

//parte logica do programa (regra de negocio)

import com.example.MiniBanco.model.Cliente;
import com.example.MiniBanco.model.ContaBancaria;
import com.example.MiniBanco.model.enums.TipoConta;
import com.example.MiniBanco.repository.ClienteRepository;
import com.example.MiniBanco.repository.ContaBancariaRepository;
import org.antlr.v4.runtime.misc.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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


    public ContaBancaria criar (Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        //Optional vai fazer com que ele retorne um cliente existente, porem caso nao ache ele ao vai voltar null, pois causaria uma erro no programa
        // nesse caso ele vai obrigar ao programador tratar esse dado, como feito embaixo com o if

        if (cliente.isEmpty()) {
            throw new RuntimeException("Cliente não encontrado");
        }

        //vai criar um objeto contabancaria, assim definindo como deve ser ao criar uma nova
        ContaBancaria conta = new ContaBancaria();
        conta.setCliente(cliente.get()); //o cliente dono da conta
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

}
