package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/usuario")
@RestController
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/Meus-dados/{id}")
    public Usuario getUsuarioById(@PathVariable int id) {
        return usuarioService.getUsuarioPorId(id);
    }


    @PutMapping("/Alterar-Meus-Dados/{id}")
    public ResponseEntity<String> modificarMeusDados(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id); // Definindo o ID do usuário a ser modificado

        boolean sucesso = usuarioService.ModificarMeusDados(usuario);
        if (sucesso) {
            return ResponseEntity.ok("Dados modificados com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao modificar os dados ");
        }
    }


    @DeleteMapping("/Excluir-Meus-Dados/{id}")
    public ResponseEntity<String> excluirMeusDados(@PathVariable Long id) {
        boolean sucesso = usuarioService.ExcluirMeusDados(id);
        if (sucesso) {
            return ResponseEntity.ok("Dados do usuário excluídos com sucesso");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir os dados do usuário");
        }
    }
}

