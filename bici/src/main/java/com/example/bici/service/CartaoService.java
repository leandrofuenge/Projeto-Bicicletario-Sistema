package com.example.bici.service;

import com.example.bici.dto.UsuarioDTO;
import com.example.bici.entity.Usuario;
import com.example.bici.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class CartaoService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CartaoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Map<String, Object> autenticarUsuario(String numeroDoCartao, int valorDoPlano) {
        Map<String, Object> response = new HashMap<>();
        UsuarioDTO usuarioDTO = obterUsuarioDTO(numeroDoCartao);
        if (usuarioDTO == null) {
            response.put("status", "Usuário não encontrado.");
            return response;
        }

        response.put("creditosRestantes", usuarioDTO.getCreditosRestantes());

        if (!verificarCreditosSuficientes(usuarioDTO, valorDoPlano)) {
            response.put("status", "Créditos insuficientes. Recarregue seu cartão.");
            return response;
        }

        response.put("status", "Usuário autenticado com sucesso! Cartão identificado. Acesso liberado.");
        return response;
    }

    private UsuarioDTO obterUsuarioDTO(String numeroDoCartao) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return new UsuarioDTO(usuario.getCreditos());
        }
        return null;
    }

    private boolean verificarCreditosSuficientes(UsuarioDTO usuarioDTO, int valorDoPlano) {
        return usuarioDTO.getCreditosRestantes() >= valorDoPlano;
    }

    public void consumirCreditoDeTodosUsuarios() {
        // Implementar lógica para consumir crédito de todos os usuários
    }
}
