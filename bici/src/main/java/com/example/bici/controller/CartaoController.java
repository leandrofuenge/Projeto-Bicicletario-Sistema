package com.example.bici.controller;

import com.example.bici.service.CartaoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
         boolean usuarioAutenticado = cartaoService.autenticarUsuario(numeroDoCartao);
         if (usuarioAutenticado) {
            return verificarCreditos(numeroDoCartao);
         }
         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
      } catch (Exception e) {
         logger.error("Ocorreu um erro durante a autenticação.", e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                 .body("Ocorreu um erro durante a autenticação.");
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
                  return ResponseEntity.ok().body("Créditos restantes do usuário: " + creditos);
               } else {
                  return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
               }
            }
         }
      } catch (SQLException e) {
         logger.error("Erro ao acessar o banco de dados.", e);
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao acessar o banco de dados: " + e.getMessage());
      }
   }

   @PostMapping("/usuarios/utilizarCredito")
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
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao acessar o banco de dados: " + e.getMessage());
      }
   }
}
