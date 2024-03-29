package com.example.bici.service;

import com.example.bici.entity.Usuario;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

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
}