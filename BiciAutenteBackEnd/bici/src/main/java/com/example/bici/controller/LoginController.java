package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<?> fazerLogin(@RequestBody Map<String, String> requestBody) {
        String cpf = requestBody.get("cpf");
        String senha = requestBody.get("senha");

        if (cpf == null || senha == null) {
            // Se o CPF ou senha estiverem ausentes no corpo da requisição
            return ResponseEntity.badRequest().body("CPF e senha são obrigatórios");
        }

        Optional<Usuario> usuarioOptional = loginService.fazerLogin(cpf, senha);
        if (usuarioOptional.isPresent()) {
            // Se o login for bem-sucedido, retorna os detalhes do usuário
            Usuario usuario = usuarioOptional.get();
            return ResponseEntity.ok(usuario);
        } else {
            // Se as credenciais estiverem incorretas, retorna um status 401 (Unauthorized)
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "CPF ou senha inválidos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}
