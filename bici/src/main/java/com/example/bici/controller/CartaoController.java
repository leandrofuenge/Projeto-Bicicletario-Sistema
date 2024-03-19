package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class CartaoController {

   private final CartaoService cartaoService;

   @Autowired
   public CartaoController(CartaoService cartaoService) {
      this.cartaoService = cartaoService;
   }

   @GetMapping("/usuarios/autenticar")
   public ResponseEntity<Object> autenticarUsuario(@RequestParam("numeroDoCartao") String numeroDoCartao) {
      try {
         // Chame o serviço para autenticar o usuário com base no número do cartão
         boolean autenticado = cartaoService.autenticarUsuario(numeroDoCartao);

         // Retorne uma resposta com base na autenticação
         if (autenticado) {
            return ResponseEntity.ok("Usuário autenticado com sucesso.");
         } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
         }
      } catch (IllegalArgumentException e) {
         // Se os parâmetros forem inválidos
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros inválidos: " + e.getMessage());
      } catch (HttpClientErrorException e) {
         // Se houver um erro na comunicação HTTP
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na requisição HTTP: " + e.getRawStatusCode());
      } catch (Exception e) {
         // Log de erro ou outras ações necessárias
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro durante a autenticação.");
      }
   }
}
