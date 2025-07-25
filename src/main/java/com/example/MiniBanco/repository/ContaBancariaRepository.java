package com.example.MiniBanco.repository;

import com.example.MiniBanco.model.ContaBancaria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long> {

    boolean existsByNumeroConta(String numeroConta);
}
