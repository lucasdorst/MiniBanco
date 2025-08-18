package com.example.MiniBanco.repository;

import com.example.MiniBanco.model.Transacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransacaoRepository extends JpaRepository<Transacao, Long> {
    List<Transacao> findByContaId(Long id);
}
