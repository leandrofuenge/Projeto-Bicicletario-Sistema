package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.AdminService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/administrador")
@CrossOrigin(origins = "*", allowedHeaders = "*") // Adicionando suporte a CORS para todas as origens e cabeçalhos
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/criar/todos/cadastro")
    @ResponseBody
    public String criarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = adminService.criarUsuario(usuario);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(novoUsuario);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Erro ao processar a requisição\"}";
        }
    }

    @GetMapping(value = "/visualizar/todos/cadastros", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String obterTodosUsuarios() {
        List<Usuario> usuarios = adminService.obterTodosUsuarios();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(usuarios);
        } catch (JsonProcessingException e) {
            return "{\"error\": \"Erro ao processar a requisição\"}";
        }
    }

    @GetMapping("/obter/todos/cpf/{cpf}")
    @ResponseBody
    public String obterUsuarioPorCpf(@PathVariable String cpf) {
        Usuario usuario = adminService.obterUsuarioPorCpf(cpf);
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

    @PutMapping("/modificar/todos/{cpf}")
    @ResponseBody
    public String modificarUsuarioPorCpf(@PathVariable String cpf, @RequestBody Usuario usuario) {
        Usuario usuarioModificado = adminService.modificarUsuarioPorCpf(cpf, usuario);
        if (usuarioModificado != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                return objectMapper.writeValueAsString(usuarioModificado);
            } catch (JsonProcessingException e) {
                return "{\"error\": \"Erro ao processar a requisição\"}";
            }
        } else {
            return "{\"error\": \"Usuário não encontrado\"}";
        }
    }

    @DeleteMapping("/excluir/todos/{cpf}")
    @ResponseBody
    public String excluirUsuarioPorCpf(@PathVariable String cpf) {
        boolean deleted = adminService.excluirUsuarioPorCpf(cpf);
        if (deleted) {
            return "{\"message\": \"Usuário excluído com sucesso\"}";
        } else {
            return "{\"error\": \"Usuário não encontrado\"}";
        }
    }



}