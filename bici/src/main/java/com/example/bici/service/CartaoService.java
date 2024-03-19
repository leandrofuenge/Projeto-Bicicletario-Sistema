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

    public boolean verificarEAtualizarCreditos(String numeroDoCartao) {
        try {
            Optional<Usuario> optionalUsuario = usuarioRepository.findByNumeroDoCartao(numeroDoCartao);
            if (optionalUsuario.isPresent()) {
                Usuario usuario = optionalUsuario.get();
                int creditos = usuario.getCreditosRestantes();

                // Atualizar créditos do usuário
                int novosCreditos = creditos + 10; // Aumentar créditos em 10 (exemplo)
                usuario.setCreditosRestantes(novosCreditos);
                usuarioRepository.save(usuario);

                System.out.println("Créditos atualizados com sucesso para o usuário com o número do cartão: " + numeroDoCartao);
                return true;
            } else {
                System.out.println("Usuário não encontrado com o número do cartão: " + numeroDoCartao);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Erro ao verificar e atualizar créditos: " + e.getMessage());
            return false;
        }
    }
}