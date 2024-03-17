package com.example.bici.service;

import com.example.bici.entity.Usuario;
import com.example.bici.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CadastroService {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public CadastroService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Criar um novo usuário
    public Usuario criarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Obter todos os usuários
    public List<Usuario> obterTodosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Obter um usuário pelo ID
    public Usuario obterUsuarioPorId(Long id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        return optionalUsuario.orElse(null);
    }

    // Atualizar um usuário existente
    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setId(id);
            return usuarioRepository.save(usuario);
        } else {
            return null;
        }
    }

    // Excluir um usuário pelo ID
    public void excluirUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
