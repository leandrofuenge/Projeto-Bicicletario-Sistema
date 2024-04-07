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
     * Verifica se um usuário com o número do cartão fornecido está autenticado no sistema.
     *
     * @param numeroDoCartao O número do cartão do usuário a ser verificado.
     * @return true se o usuário estiver autenticado, false caso contrário.
     */
    public boolean autenticarUsuario(String numeroDoCartao) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
        return optionalUsuario.isPresent(); // Retorna true se o usuário com o número do cartão existir
    }

    /**
     * Verifica os créditos restantes de um usuário com base no número do cartão fornecido.
     *
     * @param numeroDoCartao O número do cartão do usuário.
     * @return O número de créditos restantes do usuário, ou -1 se o usuário não for encontrado.
     */
    public int verificarCreditos(String numeroDoCartao) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return usuario.getCreditosRestantes();
        } else {
            return -1; // Retorna -1 se o usuário não for encontrado
        }
    }
}
