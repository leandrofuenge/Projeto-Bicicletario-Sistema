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
@RequestMapping("/usuarios")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }


    // Esta Funcao permite o Usuario criar um perfil
    @PostMapping("/criar")
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

    // Funcao Serve Apenas para Teste | Tipo: Puxar todos os clientes
    @GetMapping(value = "/todos", produces = MediaType.APPLICATION_JSON_VALUE)
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


    @GetMapping("/cpf/{cpf}")
    @ResponseBody
    public String obterUsuarioPorCpf(@PathVariable String cpf) {
        Usuario usuario = adminService.obterUsuarioPorCpf(cpf); // Modificado para obter usuário por CPF
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


    //Atualizar usuario por CPF
    @PutMapping("atualizar/{cpf}")
    @ResponseBody
    public String atualizarUsuarioPorCpf(@PathVariable String cpf, @RequestBody Usuario usuario) {
        Usuario usuarioAtualizado = adminService.atualizarUsuarioPorCpf(cpf, usuario); // Modificado para usar CPF
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


    //Excluir Usuario Por CPF
    @DeleteMapping("/excluir/{cpf}")
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
