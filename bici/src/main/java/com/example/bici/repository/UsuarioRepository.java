package com.example.bici.repository;

import com.example.bici.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByNumeroDoCartao(String numeroDoCartao);
}