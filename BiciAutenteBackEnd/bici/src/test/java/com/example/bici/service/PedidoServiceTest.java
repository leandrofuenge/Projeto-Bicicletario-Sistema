package com.example.bici.service;

import com.example.bici.entity.Usuario;
import com.example.bici.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PedidoServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private PedidoService pedidoService;

    @Test
    public void testSalvarEstadoPedido() {
        // Arrange
        int estadoPedido = 7;

        // Act
        String result = pedidoService.salvarEstadoPedido(estadoPedido);

        // Assert
        assertEquals("Estado do pedido salvo com sucesso.", result);
    }

    @Test
    public void testSolicitarCartaoPedidoPorCPF_UsuarioEncontrado() {
        // Arrange
        String cpf = "123.456.789-00";
        Usuario usuario = new Usuario();
        usuario.setCpf(cpf);
        String numeroDoCartao = "7CD6946086213703";
        when(usuarioRepository.findByCpf(cpf)).thenReturn(Optional.of(usuario));

        // Act
        String result = pedidoService.solicitarCartaoPedidoPorCPF(cpf);

        // Assert
        assertEquals(numeroDoCartao, result);
        assertEquals(numeroDoCartao, usuario.getNumeroDoCartao());
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testSolicitarCartaoPedidoPorCPF_UsuarioNaoEncontrado() {
        // Arrange
        String cpf = "123.456.789-00";
        when(usuarioRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        // Act
        String result = pedidoService.solicitarCartaoPedidoPorCPF(cpf);

        // Assert
        assertEquals("Não há usuário cadastrado com o CPF fornecido.", result);
        verify(usuarioRepository, never()).save(any());
    }
}
