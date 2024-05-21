package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.LoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Optional;

@RestController
@Tag(name = "Login Controller", description = "Endpoints para autenticação de usuários")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }


    @Operation(summary = "Realiza login de usuário")
    @PostMapping("/login")
    public ResponseEntity<?> fazerLogin(@RequestBody Map<String, String> requestBody) {
        // Validação de entrada
        String cpf = requestBody.get("cpf");
        String senha = requestBody.get("senha");
        if (cpf == null || senha == null || cpf.isEmpty() || senha.isEmpty()) {
            return ResponseEntity.badRequest().body("CPF e senha são obrigatórios");
        }

        try {
            // Autenticação segura
            Optional<Usuario> usuarioOptional = loginService.fazerLogin(cpf, senha);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                logger.info("Login realizado com sucesso", usuario);
                return ResponseEntity.ok(usuario);
            } else {
                // Resposta para credenciais inválidas
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("CPF ou senha inválidos");
            }
        } catch (Exception e) {
            // Tratamento de exceções
            logger.error("Erro ao processar login", e.getMessage());
        }
        return null;
    }
}
