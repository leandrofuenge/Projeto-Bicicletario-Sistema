package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class AdminControllerTest {

    private MockMvc mockMvc;

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adminController).build();
    }

    @Test
    void testCriarUsuario() throws Exception {
        Usuario usuario = new Usuario();
        usuario.setNome("Teste");

        when(adminService.criarUsuario(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/administrador/criar/todos/usuarios/cadastro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Teste\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário criado com sucesso!"));

        verify(adminService, times(1)).criarUsuario(any(Usuario.class));
    }

    @Test
    void testObterTodosUsuarios() throws Exception {
        Usuario usuario1 = new Usuario();
        usuario1.setNome("Usuário 1");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Usuário 2");

        List<Usuario> usuarios = Arrays.asList(usuario1, usuario2);

        when(adminService.obterTodosUsuarios()).thenReturn(usuarios);

        mockMvc.perform(get("/administrador/visualizar/todos/usuarios/cadastros"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].nome").value("Usuário 1"))
                .andExpect(jsonPath("$[1].nome").value("Usuário 2"));

        verify(adminService, times(1)).obterTodosUsuarios();
    }

    @Test
    void testObterUsuarioPorCpf() throws Exception {
        String cpf = "123456789";

        Usuario usuario = new Usuario();
        usuario.setNome("Usuário com CPF");

        when(adminService.obterUsuarioPorCpf(cpf)).thenReturn(usuario);

        mockMvc.perform(get("/administrador/obter/todos/usuarios/cpf/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(content().string("CPF obtido com sucesso"));

        verify(adminService, times(1)).obterUsuarioPorCpf(cpf);
    }

    @Test
    void testModificarUsuarioPorCpf() throws Exception {
        String cpf = "123456789";
        Usuario usuario = new Usuario();
        usuario.setNome("Novo Nome");

        when(adminService.modificarUsuarioPorCpf(eq(cpf), any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(put("/administrador/modificar/todos/usuarios/{cpf}", cpf)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\":\"Novo Nome\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário modificado com sucesso!"));

        verify(adminService, times(1)).modificarUsuarioPorCpf(eq(cpf), any(Usuario.class));
    }

    @Test
    void testExcluirUsuarioPorCpf() throws Exception {
        String cpf = "123456789";

        when(adminService.excluirUsuarioPorCpf(cpf)).thenReturn(true);

        mockMvc.perform(delete("/administrador/excluir/todos/usuarios/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(content().string("Usuário excluído com sucesso!"));

        verify(adminService, times(1)).excluirUsuarioPorCpf(cpf);
    }
}
