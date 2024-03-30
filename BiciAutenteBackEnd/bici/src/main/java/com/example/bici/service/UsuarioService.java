package com.example.bici.service;

import com.example.bici.entity.Usuario;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class UsuarioService {

    private static final Logger LOGGER = Logger.getLogger(UsuarioService.class.getName());

    private static final String DATABASE_URL = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
    private static final String DATABASE_USER = "LEANDRO";
    private static final String DATABASE_PASSWORD = "8YxeV6wCA9H8";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
    }

    private void setUsuarioParameters(PreparedStatement stmt, Usuario usuario) throws SQLException {
        stmt.setString(1, usuario.getNomeCompleto());
        stmt.setInt(2, usuario.getCreditosRestantes());
        stmt.setString(3, usuario.getCpf());
        stmt.setString(4, usuario.getRg());
        stmt.setString(5, usuario.getDataDeNascimento());
        stmt.setString(6, usuario.getSexo());
        stmt.setString(7, usuario.getCep());
        stmt.setString(8, usuario.getEndereco());
        stmt.setString(9, usuario.getNumero());
        stmt.setString(10, usuario.getBairro());
        stmt.setString(11, usuario.getCidade());
        stmt.setString(12, usuario.getEstado());
        stmt.setString(13, usuario.getEmail());
        stmt.setString(14, usuario.getNumeroDeSerieBicicleta());
        stmt.setString(15, usuario.getCorDaBicicleta());
        stmt.setString(16, usuario.getSenha());
        stmt.setString(17, usuario.getCelular());
    }

    private Usuario mapResultSetToUsuario(ResultSet rs) throws SQLException {
        Usuario usuario = new Usuario();
        usuario.setId(rs.getLong("id"));
        usuario.setNomeCompleto(rs.getString("nome_completo"));
        usuario.setCreditosRestantes(rs.getInt("creditos_restantes"));
        usuario.setCpf(rs.getString("cpf"));
        usuario.setRg(rs.getString("rg"));
        usuario.setDataDeNascimento(rs.getString("data_de_nascimento"));
        usuario.setSexo(rs.getString("sexo"));
        usuario.setCep(rs.getString("cep"));
        usuario.setEndereco(rs.getString("endereco"));
        usuario.setNumero(rs.getString("numero"));
        usuario.setBairro(rs.getString("bairro"));
        usuario.setCidade(rs.getString("cidade"));
        usuario.setEstado(rs.getString("estado"));
        usuario.setEmail(rs.getString("email"));
        usuario.setNumeroDeSerieBicicleta(rs.getString("numero_de_serie_bicicleta"));
        usuario.setCorDaBicicleta(rs.getString("cor_da_bicicleta"));
        usuario.setSenha(rs.getString("senha"));
        usuario.setCelular(rs.getString("celular"));
        return usuario;
    }

    public boolean criarMeuCadastro(Usuario novoUsuario) {
        String sql = "INSERT INTO USUARIO (NOME_COMPLETO, CREDITOS_RESTANTES, CPF, RG, DATA_DE_NASCIMENTO, SEXO, CEP, ENDERECO, NUMERO, BAIRRO, CIDADE, ESTADO, EMAIL, NUMERO_DE_SERIE_BICICLETA, COR_DA_BICICLETA, SENHA, CELULAR) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            setUsuarioParameters(stmt, novoUsuario);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 1) {
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    novoUsuario.setId(generatedKeys.getLong(1));
                    return true;
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao criar o cadastro do usuário", e);
        }
        return false;
    }

    public Usuario getUsuarioPorId(int idUsuarioLogado) {
        Usuario usuario = null;
        String sql = "SELECT id, NOME_COMPLETO, CREDITOS_RESTANTES, CPF, RG, DATA_DE_NASCIMENTO, SEXO, CEP, ENDERECO, NUMERO, BAIRRO, CIDADE, ESTADO, EMAIL, NUMERO_DE_SERIE_BICICLETA, COR_DA_BICICLETA, SENHA, CELULAR FROM USUARIO WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUsuarioLogado);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    usuario = mapResultSetToUsuario(rs);
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao recuperar o usuário por ID", e);
        }

        return usuario;
    }

    public boolean modificarMeusDados(Usuario usuario) {
        String sql = "UPDATE USUARIO SET NOME_COMPLETO = ?, CREDITOS_RESTANTES = ?, CPF = ?, RG = ?, DATA_DE_NASCIMENTO = ?, SEXO = ?, CEP = ?, ENDERECO = ?, NUMERO = ?, BAIRRO = ?, CIDADE = ?, ESTADO = ?, EMAIL = ?, NUMERO_DE_SERIE_BICICLETA = ?, COR_DA_BICICLETA = ?, SENHA = ?, CELULAR = ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            setUsuarioParameters(stmt, usuario);
            stmt.setLong(18, usuario.getId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao modificar os dados do usuário", e);
            return false;
        }
    }

    public boolean excluirMeusDados(long idUsuario) {
        String sql = "DELETE FROM USUARIO WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setLong(1, idUsuario);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao excluir os dados do usuário", e);
            return false;
        }
    }

}