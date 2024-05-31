package com.example.bici;

import com.example.bici.entity.Usuario;
import com.example.bici.service.LoginService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private LoginService loginService;

    @Test
    public void whenValidCredentials_thenReturns200() throws Exception {
        // Mocking login service
        Usuario mockUser = new Usuario();
        mockUser.setCpf("12345678900");
        mockUser.setSenha("senhaSegura");
        when(loginService.fazerLogin(anyString(), anyString())).thenReturn(Optional.of(mockUser));

        // Mock login request
        String loginRequest = "{ \"cpf\": \"12345678900\", \"senha\": \"senhaSegura\" }";

        // Perform login request
        ResultActions resultActions = mockMvc.perform(post("/usuario/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cpf").value("12345678900"));
    }

    @Test
    public void whenInvalidCredentials_thenReturns401() throws Exception {
        // Mocking login service
        when(loginService.fazerLogin(anyString(), anyString())).thenReturn(Optional.empty());

        // Mock login request
        String loginRequest = "{ \"cpf\": \"12345678900\", \"senha\": \"senhaIncorreta\" }";

        // Perform login request
        mockMvc.perform(post("/usuario/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequest))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void whenEmptyCredentials_thenReturns400() throws Exception {
        // Mock login request
        String loginRequest = "{ \"cpf\": \"\", \"senha\": \"\" }";

        // Perform login request
        mockMvc.perform(post("/usuario/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginRequest))
                .andExpect(status().isBadRequest());
    }
}
