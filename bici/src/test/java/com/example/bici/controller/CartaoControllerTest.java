package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartaoControllerTest {

    @Test
    void testVerificarCreditos() {
        // Criando uma instância simulada de CartaoService para uso no teste
        CartaoService cartaoService = new CartaoService(null); // Você pode passar null, pois não estamos usando o repository no teste

        // Criando uma instância de CartaoController com o CartaoService simulado
        CartaoController cartaoController = new CartaoController(cartaoService);

        // Agora você pode prosseguir com os testes
        // Suponha que o número do cartão exista no banco de dados para este teste
        String numeroDoCartaoExistente = "6318BC1F";
        assertTrue(cartaoController.verificarCreditos(numeroDoCartaoExistente));

        // Suponha que o número do cartão não exista no banco de dados para este teste
        String numeroDoCartaoInexistente = "9876543210";
        assertFalse(cartaoController.verificarCreditos(numeroDoCartaoInexistente));
    }





}
