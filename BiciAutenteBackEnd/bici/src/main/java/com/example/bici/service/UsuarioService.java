package com.example.bici.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    // Método para criar um novo usuário
    public boolean criarMeuUsuario(String nomeCompleto, String numeroDoCartao, int creditosRestantes, String cpf, String rg,
                                   String dataDeNascimento, char sexo, String cep, String endereco, String numero,
                                   String bairro, String cidade, String estado, String email, String numeroDeSerieBicicleta,
                                   String corDaBicicleta, String senha, String celular) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Conectar ao banco de dados
            connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XEPDB1", "LEANDRO", "8YxeV6wCA9H8");

            // Definir a consulta SQL para inserir um novo usuário
            String sql = "INSERT INTO USUARIO (nome_completo, numero_do_cartao, creditos_restantes, cpf, rg, data_de_nascimento, " +
                    "sexo, cep, endereco, numero, bairro, cidade, estado, email, numero_de_serie_bicicleta, cor_da_bicicleta, senha, celular) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            // Preparar a instrução SQL
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, nomeCompleto);
            preparedStatement.setString(2, numeroDoCartao);
            preparedStatement.setInt(3, creditosRestantes);
            preparedStatement.setString(4, cpf);
            preparedStatement.setString(5, rg);
            preparedStatement.setString(6, dataDeNascimento);
            preparedStatement.setString(7, String.valueOf(sexo));
            preparedStatement.setString(8, cep);
            preparedStatement.setString(9, endereco);
            preparedStatement.setString(10, numero);
            preparedStatement.setString(11, bairro);
            preparedStatement.setString(12, cidade);
            preparedStatement.setString(13, estado);
            preparedStatement.setString(14, email);
            preparedStatement.setString(15, numeroDeSerieBicicleta);
            preparedStatement.setString(16, corDaBicicleta);
            preparedStatement.setString(17, senha);
            preparedStatement.setString(18, celular);

            // Executar a consulta
            int rowsAffected = preparedStatement.executeUpdate();

            // Verificar se o usuário foi criado com sucesso
            if (rowsAffected > 0) {
                System.out.println("Usuário criado com sucesso.");
                return true;
            } else {
                System.out.println("Falha ao criar o usuário.");
                return false;
            }
        } catch (SQLException e) {

            return false;
        } finally {
            // Fechar conexão e instrução SQL
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException _) {

            }
        }
    }
}
