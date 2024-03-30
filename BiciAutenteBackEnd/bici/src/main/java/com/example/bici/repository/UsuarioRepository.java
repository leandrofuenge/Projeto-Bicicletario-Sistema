package com.example.bici.repository;

import com.example.bici.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNumeroDoCartao(String numeroDoCartao);
    Optional<Usuario> findByCpfAndSenha(String cpf, String senha);
    Optional<Usuario> findByCpf(String cpf);

    // Outros métodos de consulta para o usuário, se houver

    @Transactional
    @Modifying
    @Query("UPDATE Usuario u SET u.creditosRestantes = u.creditosRestantes - 1 WHERE u.numeroDoCartao = ?1 AND u.creditosRestantes > 0")
    void utilizarCredito(String numeroDoCartao);
}
