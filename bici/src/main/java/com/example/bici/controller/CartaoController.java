package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class CartaoController {

   private final CartaoService cartaoService;

   @Autowired
   public CartaoController(CartaoService cartaoService) {
      this.cartaoService = cartaoService;
   }

   /**
    * Endpoint para autenticar um usuário por número do cartão.
    *
    * @param numeroDoCartao Número do cartão do usuário
    * @param valorDoPlano   Valor do plano para autenticação
    * @return Mapa contendo o status da autenticação e os créditos restantes do usuário
    */
   @GetMapping("/usuarios/autenticar")
   public Map<String, Object> autenticarUsuarioPorNumeroDoCartao(@RequestParam String numeroDoCartao,
                                                                 @RequestParam int valorDoPlano) {
      return cartaoService.autenticarUsuario(numeroDoCartao, valorDoPlano);
   }

   /**
    * Método agendado para consumir créditos periodicamente.
    */
   @Scheduled(fixedRate = 3600000) // 1 hora = 3600000 milissegundos
   public void consumirCreditoPeriodicamente() {
      cartaoService.consumirCreditoDeTodosUsuarios();
   }
}
