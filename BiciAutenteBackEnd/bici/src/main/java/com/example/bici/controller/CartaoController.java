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
@RequestMapping("/api/cartao")
public class CartaoController {

    private final CartaoService cartaoService;
    private final Logger logger = LoggerFactory.getLogger(CartaoController.class);

    @Autowired
    public CartaoController(CartaoService cartaoService) {
        this.cartaoService = cartaoService;
    }

    @GetMapping("/usuarios/autenticar")
    public ResponseEntity<Object> autenticarUsuario(@RequestParam("numeroDoCartao") String numeroDoCartao) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "5Q8[7Ie+uN7^")) {
            String consulta = "SELECT liberado, bloqueado_desbloqueado FROM usuario WHERE numero_do_cartao = ?";
            try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                statement.setString(1, numeroDoCartao);
                try (ResultSet resultSet = statement.executeQuery()) {
                    boolean cartaoBloqueado = false;
                    boolean cartaoLiberado = false;
                    while (resultSet.next()) {
                        int liberado = resultSet.getInt("liberado");
                        int bloqueadoDesbloqueado = resultSet.getInt("bloqueado_desbloqueado");

                        if (liberado == 0 && bloqueadoDesbloqueado == 0) {
                            cartaoLiberado = true;
                        } else if (liberado == 0 && bloqueadoDesbloqueado == 1) {
                            cartaoBloqueado = true;
                        } else if (liberado == 1 && bloqueadoDesbloqueado == 1) {
                            cartaoBloqueado = true;
                        } else if (liberado == 1 && bloqueadoDesbloqueado == 0) {
                            cartaoBloqueado = true;
                        }
                    }

                    if (cartaoBloqueado) {
                        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Cartão Bloqueado.");
                    }

                    if (cartaoLiberado) {
                        return ResponseEntity.ok("Cartão Liberado.");
                    }

                    boolean usuarioAutenticado = cartaoService.autenticarUsuario(numeroDoCartao);
                    if (usuarioAutenticado) {
                        return ResponseEntity.ok("Usuário Autenticado");
                    }

                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuário não Encontrado.");
                }
            }
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
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "5Q8[7Ie+uN7^")) {
            String consulta = "SELECT creditos_restantes FROM usuario WHERE numero_do_cartao = ?";
            try (PreparedStatement statement = connection.prepareStatement(consulta)) {
                statement.setString(1, numeroDoCartao);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int creditos = resultSet.getInt("creditos_restantes");
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
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "5Q8[7Ie+uN7^")) {
            String consultaCreditos = "SELECT creditos_restantes FROM usuario WHERE numero_do_cartao = ?";
            try (PreparedStatement statement = connection.prepareStatement(consultaCreditos)) {
                statement.setString(1, numeroDoCartao);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int creditos = resultSet.getInt("creditos_restantes");
                        if (creditos > 0) {
                            String consultaAtualizacao = "UPDATE usuario SET creditos_restantes = creditos_restantes - 1 WHERE numero_do_cartao = ? AND creditos_restantes > 0";
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
