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
     * @param NUMERO_DO_CARTAO O número do cartão do usuário a ser verificado.
     * @return true se o usuário estiver autenticado, false caso contrário.
     */
    public boolean autenticarUsuario(String NUMERO_DO_CARTAO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(NUMERO_DO_CARTAO);
        return optionalUsuario.isPresent(); // Retorna true se o usuário com o número do cartão existir
    }

    /**
     * Verifica a quantidade de créditos associados ao número do cartão fornecido.
     *
     * @param numeroDoCartao O número do cartão do usuário para verificar os créditos.
     * @return A quantidade de créditos associados ao número do cartão.
     */
    public int verificarCreditos(String numeroDoCartao) {
        // Implemente a lógica para verificar os créditos do usuário com base no número do cartão
        return 0; // Valor fictício de retorno
    }
}
