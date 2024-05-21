package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);


    @PostMapping("/criar/todos/usuarios/cadastro")
    @ResponseBody
    public String criarUsuario(@RequestBody Usuario usuario) {
        try {
            logger.info("Criando novo usuário: {}", usuario);
            Usuario novoUsuario = adminService.criarUsuario(usuario);
            logger.info("Novo usuário criado: {}", novoUsuario);
            return "Usuário criado com sucesso!";
        } catch (Exception e) {
            logger.error("Erro ao criar usuario: {}", e.getMessage());
            return STR."Erro ao criar usuario: \{e.getMessage()}";
        }
    }

    @GetMapping(value = "/visualizar/todos/usuarios/cadastros", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String obterTodosUsuarios() {
        try {
            List<Usuario> usuarios = adminService.obterTodosUsuarios();
            logger.info("Obtidos usuarios cadastros com sucesso!", usuarios);
            return "Obtidos usuarios cadastros com sucesso";
        } catch (Exception e) {
            logger.error("Erro ao obter TodosUsuarios: {}", e.getMessage());
        }
        return "Erro ao obter usuarios";
    }


    @GetMapping("/obter/todos/usuarios/cpf/{cpf}")
    @ResponseBody
    public String obterUsuarioPorCpf(@PathVariable String cpf) {
        Usuario usuario = adminService.obterUsuarioPorCpf(cpf);
        if (usuario != null) {
            try {
                logger.info("CPF do usuario obtido com sucesso!", usuario);
                return "CPF obtido com sucesso";
            } catch (Exception e) {
                logger.error("Erro ao obter CPF: {}", e.getMessage());
            }
        }
        return null;
    }


    @PutMapping("/modificar/todos/usuarios/{cpf}")
    @ResponseBody
    public String modificarUsuarioPorCpf(@PathVariable String cpf, @RequestBody Usuario usuario) {
        Usuario usuarioModificado = adminService.modificarUsuarioPorCpf(cpf, usuario);
        if (usuarioModificado != null) {
            try {
                logger.info("Usuario modificado com sucesso!", usuarioModificado);
                return "Usuario modificado com sucesso!";
            } catch (Exception e) {
                logger.error("Erro ao modificar usuario: {}", e.getMessage());
                return "Erro ao modificar usuario";
            }
        }
        return null;
    }

    @DeleteMapping("/excluir/todos/usuarios/{cpf}")
    @ResponseBody
    public String excluirUsuarioPorCpf(@PathVariable String cpf) {
        boolean deleted = adminService.excluirUsuarioPorCpf(cpf);
        if (deleted) {
            try {
                logger.info("Usuario excluido com sucesso!", cpf);
                return "Usuario excluido com sucesso!";
            } catch (Exception e) {
                logger.error("Erro ao excluir usuario: {}", e.getMessage());
                return "Erro ao excluir usuario";
            }
        }
        return null;
    }
}
