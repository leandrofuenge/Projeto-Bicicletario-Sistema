package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartaoController {

   private final CartaoService usuarioService;

   @Value("${usuario.plano.200}") // Injeta o valor do plano 200 do arquivo de propriedades
   private int PLANO_200;

   @Value("${usuario.plano.300}") // Injeta o valor do plano 300 do arquivo de propriedades
   private int PLANO_300;

   @Value("${usuario.plano.400}") // Injeta o valor do plano 400 do arquivo de propriedades
   private int PLANO_400;

   @Value("${usuario.plano.500}") // Injeta o valor do plano 500 do arquivo de propriedades
   private int PLANO_500;

   @Autowired
   public CartaoController(CartaoService usuarioService) {
      this.usuarioService = usuarioService;
   }

   // Método para autenticar usuário por número do cartão
   @GetMapping("/usuarios/autenticar")
   public String autenticarUsuarioPorNumeroDoCartao(@RequestParam String numeroDoCartao,
                                                    @RequestParam int valorDoPlano) {

      // Verifica se o cartão está registrado no sistema
      boolean cartaoRegistrado = usuarioService.verificarCartaoRegistrado(numeroDoCartao);
      if (!cartaoRegistrado) {
         return "Cartão não identificado. Por favor, entre em contato com o suporte.";
      }

      // Verifica se o usuário está autenticado
      boolean autenticado = usuarioService.autenticarUsuarioPorNumeroDoCartao(numeroDoCartao);
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
      if (verificarCreditosSuficientes(numeroDoCartao, creditosNecessarios)) {
         // Se o usuário tem créditos suficientes, deduz o valor do plano dos créditos do cartão
         usuarioService.deduzirCreditos(numeroDoCartao, creditosNecessarios);

         // Obtém a quantidade restante de créditos no cartão
         int creditosRestantes = usuarioService.obterCreditosRestantes(numeroDoCartao);

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

   // Método para verificar se o usuário tem créditos suficientes
   private boolean verificarCreditosSuficientes(String numeroDoCartao, int creditosNecessarios) {
      // Lógica para verificar se o usuário tem créditos suficientes
      // Retorna true se houver créditos suficientes, false caso contrário
      return usuarioService.verificarCreditosSuficientes(numeroDoCartao, creditosNecessarios);
   }

   // Método para obter a quantidade de créditos necessários conforme o plano
   private int obterCreditosNecessarios(int valorDoPlano) {
      return switch (valorDoPlano) {
         case 200 -> PLANO_200;
         case 300 -> PLANO_300;
         case 400 -> PLANO_400;
         case 500 -> PLANO_500;
         default -> 0; // Valor do plano inválido
      };
   }

   // Método agendado para consumir créditos a cada hora
   @Scheduled(fixedRate = 3600000) // 1 hora = 3600000 milissegundos
   public void consumirCreditoPeriodicamente() {
      // Lógica para consumir créditos de todos os usuários
      usuarioService.consumirCreditoDeTodosUsuarios();
   }



   // Outros métodos podem ser adicionados aqui conforme necessário
}
