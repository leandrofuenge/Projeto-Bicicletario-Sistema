package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @PostMapping("/usuarios/criar")
    public ResponseEntity<String> criarUsuario(@RequestBody Usuario novoUsuario) {
        try {
            boolean sucesso = usuarioService.criarMeuCadastro(novoUsuario);
            if (sucesso) {
                logger.info("Usuario criado com sucesso");
                return ResponseEntity.status(HttpStatus.CREATED).body("Usuario criado com sucesso");
            } else {
                logger.error("Erro ao criar usuario");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o usuario");
            }
        } catch (Exception e) {
            logger.error("Erro ao criar usuario", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao criar o usuario");
        }
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.getUsuarioPorId(id);
            logger.info("Usuario encontrado com sucesso: {}", usuario);
            return ResponseEntity.ok(usuario);
        } catch (Exception e) {
            logger.error("Erro ao obter os dados do usuario", e);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<String> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id); // Definindo o ID do usuário a ser modificado
        try {
            boolean sucesso = usuarioService.modificarMeusDados(usuario);
            if (sucesso) {
                logger.info("Dados do usuário modificados com sucesso");
                return ResponseEntity.ok("Dados do usuário modificados com sucesso");
            } else {
                logger.error("Erro ao modificar os dados do usuário");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao modificar os dados do usuário");
            }
        } catch (Exception e) {
            logger.error("Erro ao modificar os dados do usuário", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao modificar os dados do usuário");
        }
    }

    
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<String> excluirUsuario(@PathVariable Long id) {
        try {
            boolean sucesso = usuarioService.excluirMeusDados(id);
            if (sucesso) {
                logger.info("Usuário excluído com sucesso");
                return ResponseEntity.ok("Usuário excluído com sucesso");
            } else {
                logger.error("Erro ao excluir o usuário");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o usuário");
            }
        } catch (Exception e) {
            logger.error("Erro ao excluir o usuário", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao excluir o usuário");
        }
    }

    @PutMapping("/usuarios/cartao/bloquear")
    public ResponseEntity<String> bloquearCartao(@RequestParam("numeroDoCartao") String numeroDoCartao, @RequestParam("cpf") String cpf) {
        try {
            usuarioService.bloquearCartao(numeroDoCartao, cpf);
            logger.info("Cartão bloqueado com sucesso");
            return ResponseEntity.ok("Cartão bloqueado com sucesso");
        } catch (Exception e) {
            logger.error("Erro ao bloquear o cartão", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao bloquear o cartão");
        }
    }

    @PutMapping("/usuarios/cartao/desbloquear")
    public ResponseEntity<String> desbloquearCartao(@RequestParam("numeroDoCartao") String numeroDoCartao, @RequestParam("cpf") String cpf) {
        try {
            usuarioService.desbloquearCartao(numeroDoCartao, cpf);
            logger.info("Cartão desbloqueado com sucesso");
            return ResponseEntity.ok("Cartão desbloqueado com sucesso");
        } catch (Exception e) {
            logger.error("Erro ao desbloquear o cartão", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao desbloquear o cartão");
        }
    }

    @PutMapping("/usuarios/cartao/liberar")
    public ResponseEntity<String> liberarCartao(@RequestParam("numeroDoCartao") String numeroDoCartao, @RequestParam("cpf") String cpf) {
        try {
            boolean liberacaoSucesso = usuarioService.liberarCartao(numeroDoCartao, cpf);
            if (liberacaoSucesso) {
                logger.info("Cartão liberado com sucesso");
                return ResponseEntity.ok("Cartão liberado com sucesso");
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Falha ao liberar o cartão");
            }
        } catch (Exception e) {
            logger.error("Erro ao liberar o cartão", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao liberar o cartão");
        }
    }

    @DeleteMapping("/cartao/cancelar/{cpf}/{numeroDoCartao}")
    public ResponseEntity<String> cancelarCartao(@PathVariable String cpf, @PathVariable String numeroDoCartao) {
        try {
            String resultado = usuarioService.cancelarCartao(cpf, numeroDoCartao);
            logger.info("Cartao cancelado: {}", resultado);
            return ResponseEntity.ok("Cartão cancelado com sucesso");
        } catch (Exception e) {
            logger.error("Erro ao cancelar cartao", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao cancelar cartao");
        }
    }
}
