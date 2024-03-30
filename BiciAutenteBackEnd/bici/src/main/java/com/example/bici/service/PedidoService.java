package com.example.bici.service;

import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.example.bici.entity.Usuario;
import com.example.bici.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private static final Logger LOGGER = Logger.getLogger(PedidoService.class.getName());

    private final UsuarioRepository usuarioRepository;

    public PedidoService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para salvar o estado do pedido
    public String salvarEstadoPedido(int estadoPedido) {
        try {
            switch (estadoPedido) {
                case 1:
                    LOGGER.info("Salvando o estado do pedido: Gerando Pedido");
                    break;
                case 2:
                    LOGGER.info("Salvando o estado do pedido: Pedido Gerado Com Sucesso");
                    break;
                case 3:
                    LOGGER.info("Salvando o estado do pedido: Pedido concluído com sucesso");
                    break;
                default:
                    LOGGER.warning("Estado de salvamento de pedido inválido");
                    return "Estado de salvamento de pedido inválido";
            }
            return "Estado do pedido salvo com sucesso.";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao salvar estado do pedido", e);
            return "Erro ao salvar estado do pedido";
        }
    }

    // Método para solicitar um cartão de pedido por CPF
    public String solicitarCartaoPedidoPorCPF(String cpf) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findByCpf(cpf);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                String numeroAleatorio = gerarNumeroAleatorio();
                LOGGER.info(STR."Número aleatório do tipo 7CD69460: \{numeroAleatorio}");
                usuario.setNumeroDoCartao(numeroAleatorio);
                usuarioRepository.save(usuario);
                return numeroAleatorio;
            } else {
                LOGGER.warning("Não há usuário cadastrado com o CPF fornecido.");
                return "Não há usuário cadastrado com o CPF fornecido.";
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao solicitar cartão de pedido", e);
            return "Erro ao solicitar cartão de pedido";
        }
    }

    // Função para gerar automaticamente um número aleatório do tipo 7CD69460
    private String gerarNumeroAleatorio() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            char c = (char) ('0' + random.nextInt(10));
            sb.append(c);
        }
        return STR."7CD69460\{sb.toString()}";
    }
}
