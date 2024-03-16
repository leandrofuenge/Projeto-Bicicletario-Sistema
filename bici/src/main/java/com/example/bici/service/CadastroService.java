package com.example.bici.service;

import com.example.bici.entity.Usuario;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CadastroService {

    // Simulação de um banco de dados em memória
    private final Map<Long, Usuario> usuarios = new HashMap<>();
    private long proximoId = 1;

    public Usuario criarUsuario(Usuario usuario) {
        usuario.setId(proximoId++);
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    public List<Usuario> obterTodosUsuarios() {
        return new ArrayList<>(usuarios.values());
    }

    public Usuario obterUsuarioPorId(Long id) {
        return usuarios.get(id);
    }

    public Usuario atualizarUsuario(Long id, Usuario usuario) {
        if (usuarios.containsKey(id)) {
            usuario.setId(id);
            usuarios.put(id, usuario);
            return usuario;
        }
        return null;
    }

    public void excluirUsuario(Long id) {
        usuarios.remove(id);
    }
}
