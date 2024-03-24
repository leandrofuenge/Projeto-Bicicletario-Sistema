package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.CadastroService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
public class CadastroController {

    private final CadastroService cadastroService;

    @Autowired
    public CadastroController(CadastroService cadastroService) {
        this.cadastroService = cadastroService;
    }

    @PostMapping("/criar")
    @ResponseBody
    public String criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = cadastroService.criarUsuario(usuario);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(novoUsuario);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Erro ao processar a requisição\"}";
        }
    }

    @GetMapping(value = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String obterTodosUsuarios() {
        List<Usuario> usuarios = cadastroService.obterTodosUsuarios();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(usuarios);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Erro ao processar a requisição\"}";
        }
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String obterUsuarioPorId(@PathVariable Long id) {
        Usuario usuario = cadastroService.obterUsuarioPorId(id);
        if (usuario != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(usuario);
            } catch (JsonProcessingException e) {
                return "{\"error\": \"Erro ao processar a requisição\"}";
            }
        } else {
            return "{\"error\": \"Usuário não encontrado\"}";
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public String atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = cadastroService.atualizarUsuario(id, usuario);
        if (usuarioAtualizado != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(usuarioAtualizado);
            } catch (JsonProcessingException e) {
                return "{\"error\": \"Erro ao processar a requisição\"}";
            }
        } else {
            return "{\"error\": \"Usuário não encontrado\"}";
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public String excluirUsuario(@PathVariable Long id) {
        cadastroService.excluirUsuario(id);
        return "{\"message\": \"Usuário excluído com sucesso\"}";
    }

    @PostMapping("/post")
    @ResponseBody
    public String consumirCredito(@RequestParam Long userId) {
        Usuario usuario = cadastroService.obterUsuarioPorId(userId);
        if (usuario != null) {
            usuario.consumirCredito();
            cadastroService.atualizarUsuario(usuario.getId(), usuario);
            return "{\"message\": \"Crédito consumido com sucesso\"}";
        } else {
            return "{\"error\": \"Usuário não encontrado\"}";
        }
    }
}
