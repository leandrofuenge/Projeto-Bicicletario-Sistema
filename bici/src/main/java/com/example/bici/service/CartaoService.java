package com.example.bici.service;

import com.example.bici.dto.UsuarioDTO;
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

    public boolean autenticarUsuario(String numeroDoCartao) {
        UsuarioDTO usuarioDTO = obterUsuarioDTO(numeroDoCartao);
        return usuarioDTO != null && verificarCreditosSuficientes(usuarioDTO);
    }

    private UsuarioDTO obterUsuarioDTO(String numeroDoCartao) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return new UsuarioDTO(usuario.getCreditosRestantes());
        }
        return null;
    }

    private boolean verificarCreditosSuficientes(UsuarioDTO usuarioDTO) {
        // Implemente a lógica para verificar se o usuário tem créditos suficientes
        // Esta lógica deve ser adaptada conforme necessário
        return true;
    }

    public void consumirCreditoDeTodosUsuarios() {
        // Implementar lógica para consumir crédito de todos os usuários
    }
}
