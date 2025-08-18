package com.example.MiniBanco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    private BigDecimal valor;

    private String tipo; // DEPÓSITO, SAQUE, TRANSFERÊNCIA

    private String descricao;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private ContaBancaria conta;

    public Transacao(ContaBancaria conta, BigDecimal valor, String tipo, String descricao) {
        this.valor = valor;
        this.tipo = tipo;
        this.descricao = descricao;
        this.conta = conta;
        this.dataHora = LocalDateTime.now();
    }
}
