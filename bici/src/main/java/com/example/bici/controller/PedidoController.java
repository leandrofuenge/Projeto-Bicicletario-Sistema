package com.example.bici.controller;

import com.example.bici.service.PedidoService;
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

    @PostMapping("/salvar-estado")
    public String salvarEstadoPedido(@RequestBody int estadoPedido) {
        return pedidoService.salvarEstadoPedido(estadoPedido);
    }

    @GetMapping("/solicitar-cartao/{cpf}")
    public ResponseEntity<String> solicitarCartaoPorCPF(@PathVariable String cpf) {
        String resultado = pedidoService.solicitarCartaoPedidoPorCPF(cpf);
        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultado);
        } else if (resultado.startsWith("O usuário")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resultado);
        } else {
            return ResponseEntity.ok(resultado);
        }
    }

    @DeleteMapping("/cancelar-pedido/{cpf}")
    public ResponseEntity<String> cancelarPedidoDeCartao(@PathVariable String cpf) {
        String resultado = pedidoService.cancelarPedidoDeCartao(cpf);
        if (resultado.startsWith("Erro")) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(resultado);
        } else if (resultado.startsWith("O usuário")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resultado);
        } else {
            return ResponseEntity.ok(resultado);
        }
    }
}

