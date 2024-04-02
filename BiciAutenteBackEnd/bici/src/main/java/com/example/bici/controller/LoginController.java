package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@Tag(name = "Login Controller", description = "Endpoints para autenticação de usuários")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @Operation(summary = "Realiza login de usuário")
    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody Map<String, String> requestBody) {
        String cpf = requestBody.get("cpf");
        String senha = requestBody.get("senha");

        if (cpf == null || senha == null) {
            return ResponseEntity.badRequest().body("CPF e senha são obrigatórios");
        }

        Optional<Usuario> usuarioOptional = loginService.fazerLogin(cpf, senha);
        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            return ResponseEntity.ok(usuario);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "CPF ou senha inválidos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }
}
