package com.example.MiniBanco.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity//anotação representa que essa classe vai virar uma tabela no BD
@Data // vai gerar automaticamento get, set, to string, equals e hashcode
@NoArgsConstructor //Cria construtor vazio
@AllArgsConstructor //Cria um construtor como dados, para depois criar um objeto manualmente
public class Cliente {

    @Id //faz com que variavel vire chave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto incremento
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(nullable = false, length = 100)
    private String email;


}
