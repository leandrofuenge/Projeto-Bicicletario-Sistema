package com.example.bici.controller;

import com.example.bici.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/solicitar-cartao-pedido")
    public String solicitarCartaoPedido(@RequestBody int tipoPedido) {
        return pedidoService.solicitarCartaoPedido(tipoPedido);
    }
}
