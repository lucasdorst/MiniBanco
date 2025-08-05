package com.example.MiniBanco.dto;

import lombok.Data;

import java.math.BigDecimal;

//DTO (Data Transfer Object) esse objeto de transferencia de dados serve para que o
// cliente nao tenha acesso a todos os dados da ContaBancaria, apenas o que realmente é necessário para a operação
@Data //vai gerara automaticamente get, set, toString...
public class TransacaoDTO {
    private BigDecimal valor;
    private Long origemId;
    private Long destinoId;
}
