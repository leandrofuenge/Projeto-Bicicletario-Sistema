package com.example.bici.service;

import com.example.bici.entity.Usuario;
import com.example.bici.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartaoService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CartaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Autentica um usuário com base no número do cartão fornecido.
     *
     * @param numeroDoCartao O número do cartão do usuário a ser verificado.
     * @return true se o usuário com o número do cartão existir, false caso contrition.
     */
    public boolean autenticarUsuario(String numeroDoCartao) {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
            return optionalUsuario.isPresent(); // Retorna true se o usuário com o número do cartão existir
        } catch (Exception e) {
            // Log e tratamento de exceção
            // logger.error("Erro ao autenticar usuário com o número do cartão: {}", numeroDoCartao, e);
            throw new RuntimeException("Erro ao autenticar usuário", e);
        }
    }

}
