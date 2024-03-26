package com.example.bici.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UsuarioServiceTest {

    @Test
    public void testCriarMeuUsuario() {
        // Arrange
        UsuarioService usuarioService = new UsuarioService();

        // Dados de exemplo
        String nomeCompleto = "Fulano de Tal";
        String numeroDoCartao = "1234567890123456";
        int creditosRestantes = 100;
        String cpf = "123.456.789-00";
        String rg = "12345678-9";
        String dataDeNascimento = "1990-01-01";
        char sexo = 'M';
        String cep = "12345-678";
        String endereco = "Rua Exemplo";
        String numero = "123";
        String bairro = "Bairro Exemplo";
        String cidade = "Cidade Exemplo";
        String estado = "UF";
        String email = "fulano@example.com";
        String numeroDeSerieBicicleta = "ABC123";
        String corDaBicicleta = "Vermelha";
        String senha = "senha123";
        String celular = "123456789";

        // Act
        boolean sucesso = usuarioService.criarMeuUsuario(nomeCompleto, numeroDoCartao, creditosRestantes, cpf, rg,
                dataDeNascimento, sexo, cep, endereco, numero, bairro, cidade, estado, email, numeroDeSerieBicicleta,
                corDaBicicleta, senha, celular);

        // Assert
        assertTrue(sucesso);
    }
}
