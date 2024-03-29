package com.example.bici.service;

import com.example.bici.entity.Usuario;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    static {

        Logger.getLogger(UsuarioServiceTest.class.getName());
    }

    @Test
    public void testGetUsuarioPorId() throws SQLException {
        // Preparação
        UsuarioService usuarioService = new UsuarioService();
        int idUsuario = 27;

        // Mocking das dependências do método
        Connection conn = mock(Connection.class);
        PreparedStatement stmt = mock(PreparedStatement.class);
        ResultSet rs = mock(ResultSet.class);

        // Definição do comportamento do ResultSet
        when(rs.next()).thenReturn(true);
        when(rs.getInt("id")).thenReturn(idUsuario);
        when(rs.getString("nome_completo")).thenReturn("Fulano de Tal");
        when(rs.getInt("creditos_restantes")).thenReturn(100);
        when(rs.getString("cpf")).thenReturn("123.456.789-00");

        // Simulação de chamadas JDBC bem-sucedidas
        when(conn.prepareStatement(any(String.class))).thenReturn(stmt);
        when(stmt.executeQuery()).thenReturn(rs);

        // Execução
        Usuario usuario;
        usuario = usuarioService.getUsuarioPorId(idUsuario);

        // Verificação
        assertNotNull(usuario, "Usuário não deve ser nulo");
        assertEquals(idUsuario, usuario.getId(), "O ID do usuário deve corresponder ao ID especificado");
        assertEquals("Fulano de Tal", usuario.getNomeCompleto(), "O nome completo do usuário deve ser 'Fulano de Tal'");
        assertEquals(100, usuario.getCreditosRestantes(), "Os créditos restantes do usuário devem ser 100");
        assertEquals("123.456.789-00", usuario.getCpf(), "O CPF do usuário deve ser '123.456.789-00'");
    }

    @Test
    public void testExcluirMeusDados() throws SQLException {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks

        UsuarioService usuarioService = new UsuarioService();

        // ID do usuário a ser excluído
        long idUsuario = 27;

        // Definição do comportamento do PreparedStatement
        when(connection.prepareStatement(any(String.class))).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1); // Indica que uma linha foi afetada (exclusão bem-sucedida)

        // Execução do método a ser testado
        boolean resultado = usuarioService.ExcluirMeusDados(idUsuario);

        // Verificação
        assertTrue(resultado, "A exclusão dos dados do usuário deve ser bem-sucedida");

        // Verifica se o PreparedStatement foi corretamente preparado com a consulta SQL
        verify(connection).prepareStatement(any(String.class));
        verify(preparedStatement).setLong(1, idUsuario);
        verify(preparedStatement).executeUpdate();
    }



}
