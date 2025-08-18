package com.example.MiniBanco.model;

import com.example.MiniBanco.model.enums.TipoConta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaBancaria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String numeroConta;

    private String agencia;
    private BigDecimal saldo;

    @Enumerated(EnumType.STRING) //informar que esse atributo é um enum (informa que o tipo de enum é string, entao na tabela vai aparecer "corrente" ou "poupanca"
    private TipoConta tipoConta;

    private BigDecimal historicoTransacao;

    @ManyToOne
    @JoinColumn (name = "cliente_id")
    private Cliente cliente;
}
