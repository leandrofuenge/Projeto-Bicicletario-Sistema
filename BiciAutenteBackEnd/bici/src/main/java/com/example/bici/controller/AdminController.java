package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrador")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Adicionando suporte a CORS para todas as origens e cabeçalhos
public class AdminController {

    private final AdminService adminService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/criar/todos/usuarios/cadastro")
    public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {
        try {
            logger.info("Criando novo usuário: {}", usuario);
            Usuario novoUsuario = adminService.criarUsuario(usuario);
            logger.info("Novo usuário criado: {}", novoUsuario);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário criado com sucesso!");
        } catch (Exception e) {
            logger.error("Erro ao criar usuario: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao criar usuário: \{e.getMessage()}");
        }
    }

    @GetMapping(value = "/visualizar/todos/usuarios/cadastros", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        try {
            List<Usuario> usuarios = adminService.obterTodosUsuarios();
            logger.info("Usuários cadastrados obtidos com sucesso!");
            return ResponseEntity.ok().body(usuarios);
        } catch (Exception e) {
            logger.error("Erro ao obter todos os usuários cadastrados: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/obter/todos/usuarios/cpf/{cpf}")
    public ResponseEntity<String> obterUsuarioPorCpf(@PathVariable String cpf) {
        Usuario usuario = adminService.obterUsuarioPorCpf(cpf);
        if (usuario != null) {
            try {
                logger.info("CPF do usuário obtido com sucesso: {}", usuario);
                return ResponseEntity.status(HttpStatus.OK).body("CPF obtido com sucesso");
            } catch (Exception e) {
                logger.error("Erro ao obter CPF do usuário: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao obter CPF do usuário: \{e.getMessage()}");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o CPF fornecido");
    }

    @PutMapping("/modificar/todos/usuarios/{cpf}")
    public ResponseEntity<String> modificarUsuarioPorCpf(@PathVariable String cpf, @RequestBody Usuario usuario) {
        Usuario usuarioModificado = adminService.modificarUsuarioPorCpf(cpf, usuario);
        if (usuarioModificado != null) {
            try {
                logger.info("Usuário modificado com sucesso: {}", usuarioModificado);
                return ResponseEntity.status(HttpStatus.OK).body("Usuário modificado com sucesso!");
            } catch (Exception e) {
                logger.error("Erro ao modificar usuário: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao modificar usuário: \{e.getMessage()}");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o CPF fornecido");
    }

    @DeleteMapping("/excluir/todos/usuarios/{cpf}")
    public ResponseEntity<String> excluirUsuarioPorCpf(@PathVariable String cpf) {
        boolean deleted = adminService.excluirUsuarioPorCpf(cpf);
        if (deleted) {
            try {
                logger.info("Usuário excluído com sucesso: {}", cpf);
                return ResponseEntity.status(HttpStatus.OK).body("Usuário excluído com sucesso!");
            } catch (Exception e) {
                logger.error("Erro ao excluir usuário: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao excluir usuário: \{e.getMessage()}");
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado com o CPF fornecido");
    }
}
