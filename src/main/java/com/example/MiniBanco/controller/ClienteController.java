package com.example.MiniBanco.controller;

import com.example.MiniBanco.model.Cliente;
import com.example.MiniBanco.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //indica que essa classe vai controlar as requisições da web, vai se comunicar em http e respoder em json (Rest)
@RequestMapping("/clientes") //(tipo filtro) ele define o caminho url, qualquer requisição com /clientes ele vai cair nessa classe
public class ClienteController {

    @Autowired //ingeção de dependecia, nao precisa criar um objeto, ele vai criar objeto quando for necessario
    private ClienteRepository clienteRepository;

    @PostMapping // requisições http do tipo POST vai ficar nesse metodo, Post é criar ou modificar dados, ele altera os dados
    public Cliente criar(@RequestBody Cliente cliente){ //o @ResquestBody vai falar que o conteudo recebido deve ser convertido para o objeto cliente
        return clienteRepository.save(cliente); //.save ele vai salvar esse novo cliente
    }

    @GetMapping // pegar uma informação do banco de dados, ele nao alterar os dados, requisição http do tipo Get fica aqui
    public List<Cliente> listar(){
        return clienteRepository.findAll(); // .findAll ele vai buscar todos os registros da tabela Cliente
    }
}
