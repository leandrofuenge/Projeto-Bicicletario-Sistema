package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.CadastroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        // Definir o valor inicial de creditosRestantes, por exemplo, como o número total de créditos
        usuario.setCREDITOS_RESTANTES(usuario.getCREDITOS_RESTANTES());
        Usuario novoUsuario = cadastroService.criarUsuario(usuario);
        return new ResponseEntity<>(novoUsuario, HttpStatus.CREATED);
    }

    // Obter todos os usuários
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> obterTodosUsuarios() {
        List<Usuario> usuarios = cadastroService.obterTodosUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
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
    public ResponseEntity<Void> executarPost(@RequestParam Long userId) {
        // Obter o usuário pelo ID
        Usuario usuario = cadastroService.obterUsuarioPorId(userId);
        if (usuario != null) {
            // Se o usuário existir, consumir um crédito
            usuario.consumirCredito();
            // Atualizar o usuário no banco de dados
            cadastroService.atualizarUsuario(usuario.getId(), usuario);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            // Se o usuário não existir, retornar not found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
