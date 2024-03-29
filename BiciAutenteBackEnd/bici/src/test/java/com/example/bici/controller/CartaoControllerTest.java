package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CartaoControllerTest {

    @Test
    public void testAutenticarUsuario() {
        // Arrange
        String numeroDoCartao = "1234567890";
        boolean cartaoBloqueado = false;

        CartaoService cartaoService = Mockito.mock(CartaoService.class);
        when(cartaoService.autenticarUsuario(Mockito.anyString())).thenReturn(true); // Simula autenticação bem-sucedida

        CartaoController cartaoController = new CartaoController(cartaoService);

        // Act
        ResponseEntity<Object> responseEntity = cartaoController.autenticarUsuario(numeroDoCartao, cartaoBloqueado);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Usuario Autenticado", responseEntity.getBody());
    }

    @Test
    void verificarCreditos_UsuarioEncontrado() {
        // Simulando um número de cartão existente no banco de dados
        String numeroDoCartao = "7A9F4D23";

        // Criando um controlador e chamando o método verificarCreditos
        CartaoService cartaoService = Mockito.mock(CartaoService.class);
        when(cartaoService.verificarCreditos(Mockito.anyString())).thenReturn(10); // Simula 10 créditos restantes

        CartaoController cartaoController = new CartaoController(cartaoService);
        ResponseEntity<Object> responseEntity = cartaoController.verificarCreditos(numeroDoCartao);

        // Verificando se o código de status da resposta é OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verificando se a mensagem de resposta contém os créditos restantes do usuário
        String expectedMessage = "Créditos restantes do usuário: 10";
        String responseBody = (String) responseEntity.getBody();
        assert responseBody != null;
        assertTrue(responseBody.contains(expectedMessage));
    }

    @Test
    void verificarCreditos_UsuarioNaoEncontrado() {
        // Simulando um número de cartão não existente no banco de dados
        String numeroDoCartao = "9876543210";

        // Criando um controlador e chamando o método verificarCreditos
        CartaoService cartaoService = Mockito.mock(CartaoService.class);
        when(cartaoService.verificarCreditos(Mockito.anyString())).thenReturn(-1); // Simula usuário não encontrado

        CartaoController cartaoController = new CartaoController(cartaoService);
        ResponseEntity<Object> responseEntity = cartaoController.verificarCreditos(numeroDoCartao);

        // Verificando se o código de status da resposta é NOT FOUND
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());

        // Verificando se a mensagem de resposta indica que o usuário não foi encontrado
        String expectedMessage = "Usuário não encontrado.";
        String responseBody = (String) responseEntity.getBody();
        assert responseBody != null;
        assertTrue(responseBody.contains(expectedMessage));
    }

    @Test
    void testUtilizarCredito_CreditosSuficientes() {
        // Criando um mock para CartaoService
        CartaoService cartaoService = Mockito.mock(CartaoService.class);
        // Simulando a existência de créditos suficientes
        when(cartaoService.verificarCreditos("1234567890")).thenReturn(10);

        // Criando uma instância de CartaoController com o mock de CartaoService
        CartaoController cartaoController = new CartaoController(cartaoService);

        // Chamando o método utilizarCredito
        ResponseEntity<Object> responseEntity = cartaoController.utilizarCredito("6318BC1F");

        // Verificando se a resposta é OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Crédito utilizado com sucesso.", responseEntity.getBody());
    }

}

