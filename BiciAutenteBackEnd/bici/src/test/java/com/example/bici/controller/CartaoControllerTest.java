package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CartaoControllerTest {

    @Mock
    private CartaoService cartaoService;

    @InjectMocks
    private CartaoController cartaoController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void autenticarUsuario_usuarioAutenticado() {
        String numeroDoCartao = "12345678";

        // Simulando o serviço para retornar true (usuário autenticado)
        when(cartaoService.autenticarUsuario(numeroDoCartao)).thenReturn(true);

        // Chamada ao método autenticarUsuario no controlador
        ResponseEntity<Object> response = cartaoController.autenticarUsuario(numeroDoCartao);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Usuario Autenticado", response.getBody());
    }

    @Test
    void autenticarUsuario_cartaoBloqueado() throws Exception {
        String numeroDoCartao = "12345678";

        // Simulando um cartão bloqueado no banco de dados
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("liberado")).thenReturn(0);
        when(resultSetMock.getInt("BLOQUEADO_DESBLOQUEADO")).thenReturn(1);

        PreparedStatement statementMock = mock(PreparedStatement.class);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);

        Connection connectionMock = mock(Connection.class);
        when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);

        // Substituindo a conexão no controlador pela conexão mockada
        cartaoController = new CartaoController(cartaoService) {
        };

        // Chamada ao método autenticarUsuario no controlador
        ResponseEntity<Object> response = cartaoController.autenticarUsuario(numeroDoCartao);

        // Verificações
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Cartão Bloqueado.", response.getBody());
    }

    @Test
    void verificarCreditos_creditosEncontrados() throws Exception {
        String numeroDoCartao = "12345678";

        // Simulando créditos encontrados no banco de dados
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("CREDITOS_RESTANTES")).thenReturn(10);

        PreparedStatement statementMock = mock(PreparedStatement.class);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);

        Connection connectionMock = mock(Connection.class);
        when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);

        // Substituindo a conexão no controlador pela conexão mockada
        cartaoController = new CartaoController(cartaoService) {
        };

        // Chamada ao método verificarCreditos no controlador
        ResponseEntity<Object> response = cartaoController.verificarCreditos(numeroDoCartao);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Créditos restantes do usuário: 10", response.getBody());
    }

    @Test
    void utilizarCredito_creditoUtilizadoComSucesso() throws Exception {
        String numeroDoCartao = "12345678";

        // Simulando créditos suficientes e sucesso na atualização
        ResultSet resultSetMock = mock(ResultSet.class);
        when(resultSetMock.next()).thenReturn(true);
        when(resultSetMock.getInt("CREDITOS_RESTANTES")).thenReturn(1);

        PreparedStatement statementMock = mock(PreparedStatement.class);
        when(statementMock.executeQuery()).thenReturn(resultSetMock);

        when(statementMock.executeUpdate()).thenReturn(1); // Linhas afetadas > 0

        Connection connectionMock = mock(Connection.class);
        when(connectionMock.prepareStatement(any(String.class))).thenReturn(statementMock);

        // Substituindo a conexão no controlador pela conexão mockada
        cartaoController = new CartaoController(cartaoService) {
        };

        // Chamada ao método utilizarCredito no controlador
        ResponseEntity<Object> response = cartaoController.utilizarCredito(numeroDoCartao);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Crédito utilizado com sucesso.", response.getBody());
    }
}
