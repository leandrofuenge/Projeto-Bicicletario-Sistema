package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartaoController {

   private final CartaoService cartaoService;

   @Autowired
   public CartaoController(CartaoService cartaoService) {
      this.cartaoService = cartaoService;
   }

   // Método para autenticar usuário por número do cartão
   @GetMapping("/usuarios/autenticar")
   public String autenticarUsuarioPorNumeroDoCartao(@RequestParam String numeroDoCartao,
                                                    @RequestParam int valorDoPlano) {

      // Verifica se o usuário está autenticado
      boolean autenticado = cartaoService.autenticarUsuarioPorNumeroDoCartao(numeroDoCartao);
      if (!autenticado) {
         return "Falha na autenticação do usuário.";
      }

      // Verifica se há bicicleta no bicicletário
      boolean bicicletaPresente = verificarBicicletaPresente();
      if (!bicicletaPresente) {
         return "Insira a bicicleta no bicicletário.";
      }

      // Verifica se o usuário tem créditos suficientes conforme o plano
      int creditosNecessarios = obterCreditosNecessarios(valorDoPlano);
      if (cartaoService.verificarCreditosSuficientes(numeroDoCartao, creditosNecessarios)) {
         // Se o usuário tem créditos suficientes, deduz o valor do plano dos créditos do cartão
         cartaoService.deduzirCreditos(numeroDoCartao, creditosNecessarios);

         // Obtém a quantidade restante de créditos no cartão
         int creditosRestantes = cartaoService.obterCreditosRestantes(numeroDoCartao);

         // Retorna a mensagem de autenticação com a quantidade de créditos restantes
         return "Usuário autenticado com sucesso! Cartão identificado. Acesso liberado. Créditos restantes: " + creditosRestantes;
      } else {
         return "Créditos insuficientes. Recarregue seu cartão.";
      }
   }

   // Método para verificar se há bicicleta presente no bicicletário
   private boolean verificarBicicletaPresente() {
      // Lógica para verificar a presença da bicicleta
      // Retorna true se houver bicicleta, false caso contrário
      return true; // Exemplo: sempre retorna true
   }

   // Método para obter a quantidade de créditos necessários conforme o plano
   private int obterCreditosNecessarios(int valorDoPlano) {
      // Aqui você pode implementar a lógica para obter a quantidade de créditos necessários com base no valor do plano
      // Para este exemplo, vamos retornar 0, pois não estamos utilizando essa informação
      return 0;
   }

   // Método agendado para consumir créditos a cada hora
   @Scheduled(fixedRate = 3600000) // 1 hora = 3600000 milissegundos
   public void consumirCreditoPeriodicamente() {
      // Lógica para consumir créditos de todos os usuários
      cartaoService.consumirCreditoDeTodosUsuarios();
   }

   // Outros métodos podem ser adicionados aqui conforme necessário
}
