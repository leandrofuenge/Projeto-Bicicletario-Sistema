package com.example.bici.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class PedidoService {

    private static final Logger LOGGER = Logger.getLogger(PedidoService.class.getName());

    // Função para salvar o estado do pedido
    public String salvarEstadoPedido(int estadoPedido) {
        try {
            switch (estadoPedido) {
                case 1:
                    LOGGER.info("Salvando o estado do pedido: Gerando Pedido");
                    // Lógica para salvar o estado "Gerando Pedido" no banco de dados
                    break;
                case 2:
                    LOGGER.info("Salvando o estado do pedido: Pedido Gerado Com Sucesso");
                    // Lógica para salvar o estado "Pedido Gerado Com Sucesso" no banco de dados
                    break;
                case 3:
                    LOGGER.info("Salvando o estado do pedido: Pedido concluído com sucesso");
                    // Lógica para salvar o estado "Pedido concluído com sucesso" no banco de dados
                    break;
                default:
                    LOGGER.warning("Estado de salvamento de pedido inválido");
                    return "Estado de salvamento de pedido inválido";
            }
            return "Estado do pedido salvo com sucesso."; // Sucesso
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar estado do pedido", e);
            return "Erro ao salvar estado do pedido"; // Erro
        }
    }

    // Método para solicitar um cartão de pedido
    public String solicitarCartaoPedido(int tipoPedido) {
        try {
            if (tipoPedido == 7) {
                String numeroAleatorio = gerarCartaoAleatorio();
                LOGGER.info(STR."Número aleatório do tipo 7CD69460: \{numeroAleatorio}");
                return numeroAleatorio;
            }
            LOGGER.warning("Tipo de pedido não suportado para gerar cartão automaticamente.");
            return "Tipo de pedido não suportado para gerar cartão automaticamente.";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao solicitar cartão de pedido", e);
            return "Erro ao solicitar cartão de pedido"; // Erro
        }
    }

    // Função para gerar automaticamente um número aleatório do tipo 7CD69460
    private String gerarCartaoAleatorio() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        // Gera os caracteres aleatórios
        for (int i = 0; i < 8; i++) {
            char c = (char) ('0' + random.nextInt(10));
            sb.append(c);
        }
        // Adiciona o prefixo '7CD69460'
        return STR."7CD69460\{sb.toString()}";
    }
}
