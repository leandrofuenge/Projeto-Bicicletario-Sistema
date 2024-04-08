package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.*;

import static java.lang.StringTemplate.STR;

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
         // Testable a conexão com o banco de dados Oracle
         Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.17:1521/XEPDB1", "LEANDRO", "8YxeV6wCA9H8");

         // Prepara a consulta SQL para buscar o status do cartão com base no número do cartão fornecido
         String consulta = "SELECT liberado, BLOQUEADO_DESBLOQUEADO FROM USUARIO WHERE numero_do_cartao = ?";
         PreparedStatement statement = connection.prepareStatement(consulta);
         statement.setString(1, numeroDoCartao);

         // Executa a consulta
         ResultSet resultSet = statement.executeQuery();

         // Processa o resultado da consulta
         boolean cartaoBloqueado = false;
         boolean cartaoLiberado = false;
         while (resultSet.next()) {
            int liberado = resultSet.getInt("liberado");
            int bloqueadoDesbloqueado = resultSet.getInt("BLOQUEADO_DESBLOQUEADO");

            // Adiciona a lógica para autenticar o usuário e bloquear ou desbloquear o cartão conforme especificado
            if (liberado == 0 && bloqueadoDesbloqueado == 0) {
               // Usuario autenticado e liberado
               cartaoLiberado = true;
            } else if (liberado == 0 && bloqueadoDesbloqueado == 1) {
               // Usuario bloqueado
               cartaoBloqueado = true;
            } else if (liberado == 1 && bloqueadoDesbloqueado == 1) {
               // Cartao bloqueado
               cartaoBloqueado = true;
            } else if (liberado == 1 && bloqueadoDesbloqueado == 0) {
               // Cartao bloqueado
               cartaoBloqueado = true;
            }

         }

         // Fecha recursos
         resultSet.close();
         statement.close();
         connection.close();

         if (cartaoBloqueado) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cartão Bloqueado.");
         }

         if (cartaoLiberado) {
            return ResponseEntity.ok("Cartão Liberado.");
         }

         boolean usuarioAutenticado = cartaoService.autenticarUsuario(numeroDoCartao);
         if (usuarioAutenticado) {
            return ResponseEntity.ok("Usuario Autenticado");
         }

         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não Encontrado.");
      } catch (IllegalArgumentException e) {
         logger.error("Argumento inválido ao autenticar usuário.", e);
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Argumento inválido ao autenticar usuário.");
      } catch (Exception e) {
         logger.error("Ocorreu um erro durante a autenticação.", e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro durante a autenticação.");
      }
   }

   @GetMapping("/verificarcreditos")
   public ResponseEntity<Object> verificarCreditos(@RequestParam("numeroDoCartao") String numeroDoCartao) {
      try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "LEANDRO", "8YxeV6wCA9H8")) {
         String consulta = "SELECT CREDITOS_RESTANTES FROM USUARIO WHERE NUMERO_DO_CARTAO = ?";
         try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, numeroDoCartao);
            try (ResultSet resultSet = statement.executeQuery()) {
               if (resultSet.next()) {
                  int creditos = resultSet.getInt("CREDITOS_RESTANTES");
                  return ResponseEntity.ok().body(STR."Créditos restantes do usuário: \{creditos}");
               } else {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
               }
            }
         }
      } catch (SQLException e) {
         logger.error("Erro ao acessar o banco de dados.", e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao acessar o banco de dados: \{e.getMessage()}");
      }
   }

   @PostMapping("/usuarios/utilizarcredito")
   public ResponseEntity<Object> utilizarCredito(@RequestParam("numeroDoCartao") String numeroDoCartao) {
      try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "LEANDRO", "8YxeV6wCA9H8")) {
         String consultaCreditos = "SELECT CREDITOS_RESTANTES FROM USUARIO WHERE NUMERO_DO_CARTAO = ?";
         try (PreparedStatement statement = connection.prepareStatement(consultaCreditos)) {
            statement.setString(1, numeroDoCartao);
            try (ResultSet resultSet = statement.executeQuery()) {
               if (resultSet.next()) {
                  int creditos = resultSet.getInt("CREDITOS_RESTANTES");
                  if (creditos > 0) {
                     String consultaAtualizacao = "UPDATE USUARIO SET CREDITOS_RESTANTES = CREDITOS_RESTANTES - 1 WHERE NUMERO_DO_CARTAO = ? AND CREDITOS_RESTANTES > 0";
                     try (PreparedStatement updateStatement = connection.prepareStatement(consultaAtualizacao)) {
                        updateStatement.setString(1, numeroDoCartao);
                        int linhasAfetadas = updateStatement.executeUpdate();
                        if (linhasAfetadas > 0) {
                           return ResponseEntity.ok().body("Crédito utilizado com sucesso.");
                        } else {
                           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao utilizar crédito.");
                        }
                     }
                  } else {
                     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não há créditos suficientes para utilizar.");
                  }
               } else {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
               }
            }
         }
      } catch (SQLException e) {
         logger.error("Erro ao acessar o banco de dados.", e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao acessar o banco de dados: \{e.getMessage()}");
      }
   }
}