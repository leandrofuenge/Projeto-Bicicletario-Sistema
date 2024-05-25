package com.example.bici.controller;

import com.example.bici.entity.Usuario;
import com.example.bici.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LoginControllerTest {

    @Mock
    private LoginService loginService;

    @InjectMocks
    private LoginController loginController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFazerLogin_Success() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("cpf", "123-456-789-00");
        requestBody.put("senha", "12345678");

        Usuario usuario = new Usuario();
        usuario.setCpf("123-456-789-00");

        when(loginService.fazerLogin("123-456-789-00", "12345678")).thenReturn(Optional.of(usuario));

        ResponseEntity<?> responseEntity = loginController.fazerLogin(requestBody);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(usuario, responseEntity.getBody());
    }

    @Test
    void testFazerLogin_InvalidCredentials() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("cpf", "123-456-789-000");
        requestBody.put("senha", "123456789");

        when(loginService.fazerLogin("123-456-789-000", "123456780")).thenReturn(Optional.empty());

        ResponseEntity<?> responseEntity = loginController.fazerLogin(requestBody);

        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("CPF ou senha inválidos", responseEntity.getBody());
    }

    @Test
    void testFazerLogin_EmptyCredentials() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("cpf", "");
        requestBody.put("senha", "");

        ResponseEntity<?> responseEntity = loginController.fazerLogin(requestBody);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("CPF e senha são obrigatórios", responseEntity.getBody());
    }

    @Test
    void testFazerLogin_Exception() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("cpf", "123-456-789-00");
        requestBody.put("senha", "12345678");

        when(loginService.fazerLogin("123-456-789-00", "12345678")).thenThrow(new RuntimeException("Service error"));

        ResponseEntity<?> responseEntity = loginController.fazerLogin(requestBody);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Erro ao processar login. Tente novamente mais target.", responseEntity.getBody());
    }
}
