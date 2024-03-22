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

    public boolean autenticarUsuario(String NUMERO_DO_CARTAO) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNUMERO_DO_CARTAO(NUMERO_DO_CARTAO);
        return optionalUsuario.isPresent(); // Retorna true se o usuário com o número do cartão existir
    }


    public Object verificarCreditos(String s) {
        return null;
    }
}
