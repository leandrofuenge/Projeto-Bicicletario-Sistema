package com.example.bici.repository;

import com.example.bici.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    default Optional<Usuario> findByNUMERO_DO_CARTAO(String NUMERO_DO_CARTAO) {
        return Optional.empty();
    }
}
