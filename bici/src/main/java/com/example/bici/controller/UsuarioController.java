package com.example.bici.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.bici.service.UsuarioService;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/autenticar/validacao")
    public String autenticarUsuarioPorNumeroDoCartao(@RequestParam String numeroDoCartao) {
        boolean autenticado = usuarioService.autenticarUsuarioPorNumeroDoCartao(numeroDoCartao);

        if (autenticado) {
            return "Usuário autenticado com sucesso!";
        } else {
            return "Falha na autenticação do usuário.";
        }
    }
}
