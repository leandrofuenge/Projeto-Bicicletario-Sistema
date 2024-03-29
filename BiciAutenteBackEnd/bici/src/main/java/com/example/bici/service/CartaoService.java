package com.example.bici.service;

import com.example.bici.entity.Usuario;
import com.example.bici.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    /**
     * Utiliza um crédito do usuário com base no número do cartão fornecido.
     *
     * @param numeroDoCartao O número do cartão do usuário.
     * @return ResponseEntity com o resultado da operação.
     */
    public ResponseEntity<Object> utilizarCredito(String numeroDoCartao) {
        int creditosRestantes = verificarCreditos(numeroDoCartao);
        if (creditosRestantes > 0) {
            usuarioRepository.utilizarCredito(numeroDoCartao);
            return ResponseEntity.ok("Crédito utilizado com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não há créditos suficientes para utilizar.");
        }
    }
}
