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

    // Método para solicitar um cartão de pedido por CPF
    public String solicitarCartaoPedidoPorCPF(String cpf) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findByCpf(cpf);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                if (usuario.getNumeroDoCartao() != null) {
                    LOGGER.info(STR."O usuário com CPF \{cpf} já possui um cartão registrado com o número: \{usuario.getNumeroDoCartao()}");
                    return STR."O usuário já possui um cartão registrado com o número: \{usuario.getNumeroDoCartao()}";
                } else {
                    String numeroAleatorio = gerarNumeroAleatorio();
                    LOGGER.info(STR."Número aleatório do tipo 7CD69460: \{numeroAleatorio}");
                    usuario.setNumeroDoCartao(numeroAleatorio);
                    usuarioRepository.save(usuario);
                    return numeroAleatorio;
                }
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

    // Método para cancelar o pedido de cartão por CPF
    public String cancelarPedidoDeCartao(String cpf) {
        try {
            Optional<Usuario> usuarioOptional = usuarioRepository.findByCpf(cpf);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                if (usuario.getNumeroDoCartao() != null) {
                    usuario.setNumeroDoCartao(null); // Remover o número do cartão
                    usuarioRepository.save(usuario);
                    LOGGER.info(STR."Pedido de cartão cancelado com sucesso para o usuário com CPF: \{cpf}");
                    return "Pedido de cartão cancelado com sucesso";
                } else {
                    LOGGER.warning(STR."O usuário com CPF \{cpf} não possui um pedido de cartão ativo.");
                    return "O usuário com CPF fornecido não possui um pedido de cartão ativo.";
                }
            } else {
                LOGGER.warning("Não há usuário cadastrado com o CPF fornecido.");
                return "Não há usuário cadastrado com o CPF fornecido.";
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro ao cancelar pedido de cartão", e);
            return "Erro ao cancelar pedido de cartão";
        }
    }
}
