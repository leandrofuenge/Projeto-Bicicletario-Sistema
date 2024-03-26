package com.example.bici.controller;

import com.example.bici.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")

public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/criar/meu/usuario")
    public void criarMeuUsuario(String nomeCompleto, String numeroDoCartao, int creditosRestantes, String cpf,
                                String rg, String dataDeNascimento, char sexo, String cep, String endereco,
                                String numero, String bairro, String cidade, String estado, String email,
                                String numeroDeSerieBicicleta, String corDaBicicleta, String senha, String celular) {
        boolean sucesso = usuarioService.criarMeuUsuario(nomeCompleto, numeroDoCartao, creditosRestantes, cpf,
                rg, dataDeNascimento, sexo, cep, endereco, numero,
                bairro, cidade, estado, email, numeroDeSerieBicicleta,
                corDaBicicleta, senha, celular);
        if (sucesso) {
            System.out.println("Usuário criado com sucesso!");
        } else {
            System.out.println("Falha ao criar usuário.");
        }
    }



}
