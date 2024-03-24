package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    // Endpoint para fazer login
    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestParam("cpf") String cpf, @RequestParam("senha") String senha) {
        Optional<Usuario> usuarioOptional = loginService.fazerLogin(cpf, senha);
        if (usuarioOptional.isPresent()) {
            // Se o login for bem-sucedido, retornar os detalhes do usuário
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Usuario Logado");
        } else {
            // Se as credenciais estiverem incorretas, retornar um status 401 (Unauthorized)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CPF ou senha inválidos");
        }
    }
}
