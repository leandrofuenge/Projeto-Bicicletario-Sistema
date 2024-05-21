package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    public String criarUsuario(@RequestBody Usuario novoUsuario) {
        try {
            boolean sucesso = usuarioService.criarMeuCadastro(novoUsuario);
            if (sucesso) {
                logger.info("Usuario criado com sucesso", true);
                return "Usuario criado com sucesso";
            } else {
                logger.error("Erro ao criar usuario", false);
                return "Erro ao criar o usuario";
            }
        } catch (Exception e) {
            return "Erro ao criar o usuario";
        }
    }

    @GetMapping("/usuarios/{id}")
    public String getUsuarioById(@PathVariable int id) {
        try {
            Usuario usuario = usuarioService.getUsuarioPorId(id);
            logger.info("Usuario encontrado com sucesso", usuario);
            return "Usuario encontrado com sucesso";
        } catch (Exception e) {

            logger.error("Erro ao obter os dados do usuario");
        }
        return null;
    }

    @PutMapping("/usuarios/{id}")
    public String modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
        usuario.setId(id); // Definindo o ID do usuário a ser modificado

        try {
            boolean sucesso = usuarioService.modificarMeusDados(usuario);
            if (sucesso) {
                logger.info("Dados do usuário modificados com sucesso");
                return "Dados do usuário modificados com sucesso";
            } else {
                logger.error("Erro ao modificar os dados do usuário");
                return "Erro ao modificar os dados do usuário";
            }
        } catch (Exception e) {
            return "Erro ao modificar os dados do usuário";
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public String excluirUsuario(@PathVariable Long id) {
        try {
            boolean sucesso = usuarioService.excluirMeusDados(id);
            if (sucesso) {
                logger.info("Usuário excluído com sucesso");
                return "Usuário excluído com sucesso";
            } else {
                logger.error("Erro ao excluir o usuário");
                return "Erro ao excluir o usuário";
            }
        } catch (Exception e) {
            return "Erro ao excluir o usuário";
        }
    }


    @PutMapping("/usuarios/cartao/bloquear")
    public String bloquearCartao(@RequestParam("numeroDoCartao") String numeroDoCartao, @RequestParam("cpf") String cpf) {
        try {
            usuarioService.bloquearCartao(numeroDoCartao, cpf);
            logger.info("Cartão bloqueado com sucesso");
            return "Cartão bloqueado com sucesso";
        } catch (Exception e) {
            return "Erro ao bloquear o cartão";
        }
    }

    @PutMapping("/usuarios/cartao/desbloquear")
    public String desbloquearCartao(@RequestParam("numeroDoCartao") String numeroDoCartao, @RequestParam("cpf") String cpf) {
        try {
            usuarioService.desbloquearCartao(numeroDoCartao, cpf);
            logger.info("Cartão desbloqueado com sucesso.");
            return "Cartão desbloqueado com sucesso.";
        } catch (Exception e) {

            return "Cartão desbloqueado com sucesso.";

        }
    }

    @PutMapping("/usuarios/cartao/liberar")
    public String liberarCartao(@RequestParam("numeroDoCartao") String numeroDoCartao, @RequestParam("cpf") String cpf) {
        boolean liberacaoSucesso = usuarioService.liberarCartao(numeroDoCartao, cpf);
        if (liberacaoSucesso) {
            logger.info("Cartão liberado com sucesso.");
            return "Cartão liberado com sucesso.";
        } else {
            return "Falha ao liberar o cartão.";
        }
    }

    @DeleteMapping("/cartao/cancelar/{cpf}/{numeroDoCartao}")
    public String cancelarCartao(@PathVariable String cpf, @PathVariable String numeroDoCartao) {

        try {
            String resultado = usuarioService.cancelarCartao(cpf, numeroDoCartao);
            logger.info("Cartao cancelado", resultado);
        } catch (Exception e){
             return "Erro ao cancelar cartao";
        }
        return null;
    }
}
