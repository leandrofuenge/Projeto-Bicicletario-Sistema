package com.example.bici.controller;

import com.example.bici.service.CartaoService;
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

   @Autowired
   public CartaoController(CartaoService cartaoService) {
      this.cartaoService = cartaoService;
   }

   @GetMapping("/usuarios/autenticar")
   public ResponseEntity<Object> autenticarUsuario(@RequestParam("numeroDoCartao") String numeroDoCartao) {
      try {
         boolean autenticado = cartaoService.autenticarUsuario(numeroDoCartao);
         if (autenticado) {
            return ResponseEntity.ok("Usuário autenticado com sucesso.");
         } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não autenticado.");
         }
      } catch (IllegalArgumentException e) {
         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parâmetros inválidos: " + e.getMessage());
      } catch (HttpClientErrorException e) {
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro na requisição HTTP: " + e.getRawStatusCode());
      } catch (Exception e) {
         e.printStackTrace();
         return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro durante a autenticação.");
      }
   }

   // Método para verificar e atualizar créditos do usuário
   public boolean verificarEAtualizarCreditos(String numeroDoCartao) {
      System.out.println("Verificando e atualizando créditos do usuário...");

      // Estabelecer conexão com o banco de dados Oracle
      try (Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "LEANDRO", "8YxeV6wCA9H8")) {
         // ...
         // Consultar o banco de dados para verificar os créditos do usuário
         String consulta = "SELECT CREDITOS_RESTANTES FROM USUARIO WHERE NUMERO_DO_CARTAO = ?";
         try (PreparedStatement statement = connection.prepareStatement(consulta)) {
            statement.setString(1, numeroDoCartao);
            try (ResultSet resultSet = statement.executeQuery()) {
               if (resultSet.next()) {
                  int creditos = resultSet.getInt("CREDITOS_RESTANTES"); // Corrigir o nome da coluna aqui
                  


         // Atualizar créditos do usuário no banco de dados
                  int novosCreditos = creditos + 10; // Aumentar créditos em 10 (exemplo)
                  String atualizacao = "UPDATE USUARIO SET CREDITOS_RESTANTES = ? WHERE NUMERO_DO_CARTAO = ?";
                  try (PreparedStatement updateStatement = connection.prepareStatement(atualizacao)) {
                     updateStatement.setInt(1, novosCreditos);
                     updateStatement.setString(2, numeroDoCartao);
                     updateStatement.executeUpdate();
                  }

                  System.out.println("Créditos atualizados com sucesso.");
                  return true;
               } else {
                  System.out.println("Usuário não encontrado.");
                  return false;
               }
            }
         }
      } catch (SQLException e) {
         System.out.println("Erro ao acessar o banco de dados: " + e.getMessage());
         return false;
      }
   }

   // Método para realizar outras operações relacionadas ao cartão
   // ...

   public static void main(String[] args) {
      CartaoService cartaoService = new CartaoService(new UsuarioRepository()); // Supondo que UsuarioRepository seja necessário para criar CartaoService
      CartaoController cartaoController = new CartaoController(cartaoService);
      boolean sucesso = cartaoController.verificarEAtualizarCreditos("DE4F0D26");
      if (sucesso) {
         System.out.println("Operação concluída com sucesso.");
      } else {
         System.out.println("Falha ao executar a operação.");
      }
   }
}
