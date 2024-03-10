package com.example.bici.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bici.repository.UsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public boolean autenticarUsuarioPorNumeroDoCartao(String numeroDoCartao) {
        // Verificar se existe um usuário com o número do cartão fornecido
        return usuarioRepository.existsByNumeroDoCartao(numeroDoCartao);
    }
}
