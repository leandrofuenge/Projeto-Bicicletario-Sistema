package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/usuarios/criar")
    public ResponseEntity<?> criarUsuario(@RequestBody Usuario novoUsuario) {
        try {
            boolean sucesso = usuarioService.criarMeuCadastro(novoUsuario);
            if (sucesso) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o usuário");
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<?> getUsuarioById(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.getUsuarioPorId(id);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao obter os dados do usuário");
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id); // Definindo o ID do usuário a ser modificado

        try {
            boolean sucesso = usuarioService.modificarMeusDados(usuario);
            if (sucesso) {
                return ResponseEntity.ok("Dados do usuário modificados com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao modificar os dados do usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao modificar os dados do usuário");
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<?> excluirUsuario(@PathVariable Long id) {
        try {
            boolean sucesso = usuarioService.excluirMeusDados(id);
            if (sucesso) {
                return ResponseEntity.ok("Usuário excluído com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o usuário");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o usuário");
        }
    }


    @PutMapping("/usuarios/cartao/bloquear")
    public ResponseEntity<String> bloquearCartao(@RequestParam("numeroDoCartao") String numeroDoCartao, @RequestParam("cpf") String cpf) {
        try {
            usuarioService.bloquearCartao(numeroDoCartao, cpf);
            return ResponseEntity.ok("Cartão bloqueado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao bloquear o cartão: \{e.getMessage()}");
        }
    }

    @PutMapping("/usuarios/cartao/desbloquear")
    public ResponseEntity<String> desbloquearCartao(@RequestParam("numeroDoCartao") String numeroDoCartao, @RequestParam("cpf") String cpf) {
        try {
            usuarioService.desbloquearCartao(numeroDoCartao, cpf);
            return ResponseEntity.ok("Cartão desbloqueado com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao desbloquear o cartão: \{e.getMessage()}");
        }
    }


    @PutMapping("/cancelar")
    public ResponseEntity<String> cancelarPedidoCartao(@RequestParam("cpf") String cpf, @RequestParam("numeroDoCartao") String numeroDoCartao) {
        String resultado = usuarioService.cancelarCartao(cpf, numeroDoCartao);
        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultado);
        } else {
            return ResponseEntity.ok(resultado);
        }
    }

}