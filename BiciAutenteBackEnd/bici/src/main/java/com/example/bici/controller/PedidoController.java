package com.example.bici.controller;

import com.example.bici.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/solicitar-cartao-por-cpf")
    public String solicitarCartaoPorCPF(@RequestParam("cpf") String cpf) {
        return pedidoService.solicitarCartaoPedidoPorCPF(cpf);
    }
}