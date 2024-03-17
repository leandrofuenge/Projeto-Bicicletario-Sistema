package com.example.bici.repository;

import com.example.bici.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNumeroDoCartao(String numeroDoCartao);

    // Método estático para encontrar um usuário por número do cartão
    static Optional<Usuario> findByNumeroDoCartaoStatic(String numeroDoCartao) {
        return Optional.empty(); // Implemente a lógica para buscar o usuário por número do cartão
    }
}
