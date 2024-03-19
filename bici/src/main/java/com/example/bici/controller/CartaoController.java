package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import java.sql.*;

@RestController
public class CartaoController {

   private final CartaoService cartaoService;
   private final Logger logger = LoggerFactory.getLogger(CartaoController.class);

   @Autowired
   public CartaoController(CartaoService cartaoService) {
      this.cartaoService = cartaoService;
   }

   @GetMapping("/usuarios/autenticar")
   public ResponseEntity<Object> autenticarUsuario(@RequestParam("numeroDoCartao") String numeroDoCartao) {
      try {
         boolean autenticado = cartaoService.autenticarUsuario(numeroDoCartao);
         if (autenticado) {
            // Adicione a chamada para verificar os créditos aqui
            boolean creditosVerificados = verificarCreditos(numeroDoCartao).hasBody();
            if (creditosVerificados) {
               return ResponseEntity.ok("Usuário autenticado com sucesso.");
            } else {
               logger.error("Erro ao verificar créditos do usuário.");
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao verificar créditos do usuário.");
            }
         } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
         }
      } catch (IllegalArgumentException e) {
         logger.error("Parâmetros inválidos: " + e.getMessage());
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros inválidos: " + e.getMessage());
      } catch (HttpClientErrorException e) {
         logger.error("Erro na requisição HTTP: " + e.getStatusCode());
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na requisição HTTP: " + e.getStatusCode());
      } catch (Exception e) {
         logger.error("Ocorreu um erro durante a autenticação.", e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro durante a autenticação.");
      }
   }

   // Método para verificar créditos do usuário


   @GetMapping("/verificarcreditos")
   public ResponseEntity<Object> verificarCreditos(@RequestParam("numeroDoCartao") String numeroDoCartao) {
      try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "LEANDRO", "8YxeV6wCA9H8")) {
         String consulta = "SELECT CREDITOS_RESTANTES FROM USUARIO WHERE NUMERO_DO_CARTAO = ?";
         try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, numeroDoCartao);
            try (ResultSet resultSet = statement.executeQuery()) {
               if (resultSet.next()) {
                  int creditos = resultSet.getInt("CREDITOS_RESTANTES");
                  return ResponseEntity.ok().body("Créditos restantes do usuário: " + creditos);
               } else {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
               }
            } catch (SQLException e) {
               return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o resultado da consulta: " + e.getMessage());
            }
         } catch (SQLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao preparar a consulta SQL: " + e.getMessage());
         }
      } catch (SQLException e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao conectar ao banco de dados: " + e.getMessage());
      }
   }
}