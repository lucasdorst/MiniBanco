package com.example.MiniBanco.repository;

import com.example.MiniBanco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

//vai conversar e lidar com o banco de dados

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
// Cliente: qual entidade ela vai manipular
// Long: qual o tipo da chave primária (id) da entidade Cliente

    boolean existsByCpf(String cpf); // vou verificar se tem algum cliente com esse cpf
}
