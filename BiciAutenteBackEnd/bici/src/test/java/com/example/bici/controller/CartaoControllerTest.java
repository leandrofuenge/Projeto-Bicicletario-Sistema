package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartaoControllerTest {

    @Test
    public void testAutenticarUsuario() {
        // Arrange
        String numeroDoCartao = "1234567890";
        boolean cartaoBloqueado = false;

        CartaoService cartaoService = Mockito.mock(CartaoService.class);
        Mockito.when(cartaoService.autenticarUsuario(Mockito.anyString())).thenReturn(true); // Simula autenticação bem-sucedida

        CartaoController cartaoController = new CartaoController(cartaoService);

        // Act
        ResponseEntity<Object> responseEntity = cartaoController.autenticarUsuario(numeroDoCartao, cartaoBloqueado);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Usuario Autenticado", responseEntity.getBody());
    }

    @Test
    public void testVerificarCreditos() {
        // Arrange

        // Simula uma conexão ao banco de dados e uma consulta bem-sucedida
        // Aqui você precisará do seu framework de teste de integração ou um mock de banco de dados para simular isso

        // Act
        // Execute a chamada ao endpoint e verifique se a resposta é a esperada

        // Assert
        // Verifique se a resposta é a esperada
    }

    @Test
    public void testUtilizarCredito() {
        // Arrange

        // Simula uma conexão ao banco de dados e uma consulta bem-sucedida
        // Aqui você precisará do seu framework de teste de integração ou um mock de banco de dados para simular isso

        // Act
        // Execute a chamada ao endpoint e verifique se a resposta é a esperada

        // Assert
        // Verifique se a resposta é a esperada
    }
}
