package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.CadastroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class CadastroController {

    private final CadastroService cadastroService;

    @Autowired
    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    // Criar um novo usuário
    @PostMapping("/criar")
    public ResponseEntity<String> criarUsuario(@RequestBody Usuario usuario) {
        usuario.setCreditosRestantes(usuario.getCreditosRestantes());
        Usuario novoUsuario = cadastroService.criarUsuario(usuario);

        ObjectMapper objectMapper = new ObjectMapper();
        String novoUsuarioJson;
        try {
            novoUsuarioJson = objectMapper.writeValueAsString(novoUsuario);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(novoUsuarioJson);
    }


    // Obter todos os usuários
    @GetMapping(value = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> obterTodosUsuarios() {
        List<Usuario> usuarios = cadastroService.obterTodosUsuarios();

        ObjectMapper objectMapper = new ObjectMapper();
        String usuariosJson;
        try {
            usuariosJson = objectMapper.writeValueAsString(usuarios);
        } catch (JsonProcessingException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok(usuariosJson);
    }

    // Obter um usuário pelo ID
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obterUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = cadastroService.obterUsuarioPorId(id);
        if (usuario != null) {
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Atualizar um usuário existente
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = cadastroService.atualizarUsuario(id, usuario);
        if (usuarioAtualizado != null) {
            return new ResponseEntity<>(usuarioAtualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Excluir um usuário pelo ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirUsuario(@PathVariable Long id) {
        cadastroService.excluirUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Consumir crédito quando um post é executado
    @PostMapping("/post")
    public ResponseEntity<Void> consumirCredito(@RequestParam Long userId) {
        Usuario usuario = cadastroService.obterUsuarioPorId(userId);
        if (usuario != null) {
            usuario.consumirCredito();
            cadastroService.atualizarUsuario(usuario.getId(), usuario);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
