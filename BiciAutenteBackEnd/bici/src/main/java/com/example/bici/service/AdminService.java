package com.example.bici.service;

import com.example.bici.entity.Usuario;
import com.example.bici.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final UsuarioRepository usuarioRepository;


    @Autowired
    public AdminService(UsuarioRepository usuarioRepository) {
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

    // Obter um usuário pelo CPF
    public Usuario obterUsuarioPorCpf(String cpf) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByCpf(cpf);
        return optionalUsuario.orElse(null);
    }

    // Atualizar um usuário existente
    public Usuario modificarUsuarioPorCpf(String cpf, Usuario usuario) {
        // Verifique se o usuário com o CPF fornecido existe no banco de dados
        Usuario usuarioExistente = obterUsuarioPorCpf(cpf);
        if (usuarioExistente != null) {
            // Atualize as informações do usuário com base nos dados fornecidos
            usuarioExistente.setNomeCompleto(usuario.getNomeCompleto());
            usuarioExistente.setEmail(usuario.getEmail());
            // Continue atualizando outros campos conforme necessário

            // Salve as alterações no banco de dados

            return usuarioRepository.save(usuarioExistente);
        } else {
            return null; // Retorne null se o usuário não existir
        }
    }


    // Excluir um usuário pelo ID
    public boolean excluirUsuarioPorCpf(String cpf) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByCpf(cpf);
        if (optionalUsuario.isPresent()) {
            usuarioRepository.delete(optionalUsuario.get());
            return true;
        } else {
            return false;
        }
    }
}



