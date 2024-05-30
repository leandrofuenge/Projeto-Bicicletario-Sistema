package com.example.bici.controller;

import com.example.bici.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    private static final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    @PostMapping("/salvar-estado")
    public String salvarEstadoPedido(@RequestBody int estadoPedido) {
        logger.info("Pedido salvo com sucesso, estado: {}", estadoPedido);
        return "Pedido salvo com sucesso";
    }

    @GetMapping("/solicitar-cartao/{cpf}")
    public ResponseEntity<String> solicitarCartaoPorCPF(@PathVariable String cpf) {
        String resultado = pedidoService.solicitarCartaoPedidoPorCPF(cpf);
        if (resultado.startsWith("Erro")) {
            logger.error("Erro ao solicitar cartao por CPF: {}", resultado);
            return new ResponseEntity<>(resultado, HttpStatus.BAD_REQUEST);
        } else if (resultado.startsWith("O usuário")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
        } else {
            logger.info("Cartao solicitado com sucesso: {}", resultado);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        }
    }

    @DeleteMapping("/cancelar-pedido/{cpf}")
    public String cancelarPedidoDeCartao(@PathVariable String cpf) {
        String resultado = pedidoService.cancelarPedidoDeCartao(cpf);
        if (resultado.startsWith("Erro")) {
            logger.error("Erro ao cancelar Cartao: {}", resultado);
            return "Erro ao cancelar cartao";
        } else if (resultado.startsWith("O usuário")) {
            return "Erro ao cancelar cartao";
        } else {
            logger.info("Cartao cancelado com sucesso");
            return "Cartao cancelado com sucesso";
        }
    }
}
