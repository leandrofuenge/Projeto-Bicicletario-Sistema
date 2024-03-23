package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    private final AuthService authService;

    @Autowired
    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    //http://localhost:8080/login?cpf=seu_cpf_aqui&senha=sua_senha_aqui
    @PostMapping("/login")
    public Optional<Usuario> fazerLogin(@RequestParam("cpf") String cpf, @RequestParam("senha") String senha) {
        return authService.fazerLogin(cpf, senha);
    }


}
