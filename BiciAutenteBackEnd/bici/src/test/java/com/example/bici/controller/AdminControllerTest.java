package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.AdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



public class AdminControllerTest {

    @Test
    public void testCriarUsuario() throws Exception {
        // Arrange
        AdminService adminService = Mockito.mock(AdminService.class); // Mock do serviço
        Usuario usuario = new Usuario(); // Criando um usuário de exemplo
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUsuario = objectMapper.writeValueAsString(usuario); // Convertendo o usuário para JSON

        Mockito.when(adminService.criarUsuario(Mockito.any(Usuario.class))).thenReturn(usuario); // Mock do método criarUsuario

        AdminController adminController = new AdminController(adminService); // Criando o controlador
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(adminController).build(); // Configurando o MockMvc

        // Act & Assert
        mockMvc.perform(post("/administrador/criar/todos/cadastro")
                        .content(jsonUsuario)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera status OK (200)
                .andExpect(content().json(jsonUsuario)); // Espera que o conteúdo retornado seja igual ao JSON do usuário
    }


    @Test
    public void testObterTodosUsuarios() throws Exception {
        // Arrange
        AdminService adminService = Mockito.mock(AdminService.class); // Mock do serviço
        List<Usuario> usuarios = new ArrayList<>(); // Lista de usuários de exemplo
        Usuario usuario1 = new Usuario();
        usuario1.setNomeCompleto("Fulano");
        usuarios.add(usuario1);
        Usuario usuario2 = new Usuario();
        usuario2.setNomeCompleto("Ciclano");
        usuarios.add(usuario2);

        Mockito.when(adminService.obterTodosUsuarios()).thenReturn(usuarios); // Mock do método obterTodosUsuarios

        AdminController adminController = new AdminController(adminService); // Criando o controlador
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(adminController).build(); // Configurando o MockMvc

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUsuarios = objectMapper.writeValueAsString(usuarios); // Convertendo a lista de usuários para JSON

        // Act & Assert
        mockMvc.perform(get("/administrador/visualizar/todos/cadastros")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera status OK (200)
                .andExpect(content().json(jsonUsuarios)); // Espera que o conteúdo retornado seja igual ao JSON da lista de usuários
    }


    @Test
    public void testObterUsuarioPorCpf_UsuarioEncontrado() throws Exception {
        // Arrange
        AdminService adminService = Mockito.mock(AdminService.class); // Mock do serviço
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto("Fulano");
        String cpf = "12345678900";

        Mockito.when(adminService.obterUsuarioPorCpf(cpf)).thenReturn(usuario); // Mock do método obterUsuarioPorCpf

        AdminController adminController = new AdminController(adminService); // Criando o controlador
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(adminController).build(); // Configurando o MockMvc

        ObjectMapper objectMapper = new ObjectMapper();
        String jsonUsuario = objectMapper.writeValueAsString(usuario); // Convertendo o usuário para JSON

        // Act & Assert
        mockMvc.perform(get("/administrador/obter/todos/cpf/{cpf}", cpf))
                .andExpect(status().isOk()) // Espera status OK (200)
                .andExpect(content().json(jsonUsuario)); // Espera que o conteúdo retornado seja igual ao JSON do usuário
    }

    @Test
    public void testObterUsuarioPorCpf_UsuarioNaoEncontrado() throws Exception {
        // Arrange
        AdminService adminService = Mockito.mock(AdminService.class); // Mock do serviço
        String cpf = "12345678900";

        Mockito.when(adminService.obterUsuarioPorCpf(cpf)).thenReturn(null); // Mock do método obterUsuarioPorCpf

        AdminController adminController = new AdminController(adminService); // Criando o controlador
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(adminController).build(); // Configurando o MockMvc

        // Act & Assert
        mockMvc.perform(get("/administrador/obter/todos/cpf/{cpf}", cpf))
                .andExpect(status().isOk()) // Espera status OK (200)
                .andExpect(content().json("{\"error\": \"Usuário não encontrado\"}")); // Espera que o conteúdo retornado seja um JSON indicando usuário não encontrado
    }


    @Test
    public void testModificarUsuarioPorCpf() throws Exception {
        // Arrange
        AdminService adminService = Mockito.mock(AdminService.class); // Mock do serviço
        Usuario usuarioModificado = new Usuario(); // Criando um usuário de exemplo modificado
        usuarioModificado.setNomeCompleto("Usuário Modificado");

        Mockito.when(adminService.modificarUsuarioPorCpf(Mockito.anyString(), Mockito.any(Usuario.class)))
                .thenReturn(usuarioModificado); // Mock do método modificarUsuarioPorCpf

        AdminController adminController = new AdminController(adminService); // Criando o controlador
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(adminController).build(); // Configurando o MockMvc

        String jsonUsuarioModificado = new ObjectMapper().writeValueAsString(usuarioModificado); // Convertendo o usuário modificado para JSON

        // Act & Assert
        mockMvc.perform(put("/administrador/modificar/todos/12345678900")
                        .content(jsonUsuarioModificado)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera status OK (200)
                .andExpect(content().json(jsonUsuarioModificado)); // Espera que o conteúdo retornado seja igual ao JSON do usuário modificado
    }

    @Test
    public void testExcluirUsuarioPorCpf() throws Exception {
        // Arrange
        AdminService adminService = Mockito.mock(AdminService.class); // Mock do serviço
        Mockito.when(adminService.excluirUsuarioPorCpf(Mockito.anyString())).thenReturn(true); // Mock do método excluirUsuarioPorCpf

        AdminController adminController = new AdminController(adminService); // Criando o controlador
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(adminController).build(); // Configurando o MockMvc

        // Act & Assert
        mockMvc.perform(delete("/administrador/excluir/todos/12345678900")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Espera status OK (200)
                .andExpect(content().json("{\"message\": \"Usuário excluído com sucesso\"}")); // Espera que o conteúdo retornado seja igual à mensagem de sucesso
    }


}
