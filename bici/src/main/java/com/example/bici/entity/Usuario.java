package com.example.bici.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto; // Tipo String

    @Column(nullable = false)
    private String numeroDoCartao; // Tipo String

    private int creditos; // Tipo int

    private String valorDoPlano; // Tipo String

    @Column(nullable = false, unique = true)
    private String cpf; // Tipo String

    @Column(nullable = false)
    private String rg; // Tipo String

    @Column(nullable = false)
    private String dataDeNascimento; // Tipo String

    @Column(nullable = false)
    private String sexo; // Tipo String

    @Column(nullable = false)
    private String cep; // Tipo String

    @Column(nullable = false)
    private String endereco; // Tipo String

    @Column(nullable = false)
    private String numero; // Tipo String

    @Column(nullable = false)
    private String bairro; // Tipo String

    @Column(nullable = false)
    private String cidade; // Tipo String

    @Column(nullable = false)
    private String estado; // Tipo String

    @Column(nullable = false, unique = true)
    private String email; // Tipo String

    @Column(nullable = false)
    private String senha; // Tipo String

    @Column(nullable = false)
    private String celular; // Tipo String

    public Usuario() {
        // Construtor padrão necessário para JPA
    }

    // Construtor com todos os campos
    public Usuario(String nomeCompleto, String email, String numeroDoCartao, int creditos, String valorDoPlano,
                   String cpf, String rg, String dataDeNascimento, String sexo, String cep, String endereco,
                   String numero, String bairro, String cidade, String estado, String senha, String celular) {
        this.nomeCompleto = nomeCompleto;
        this.email = email;
        this.numeroDoCartao = numeroDoCartao;
        this.creditos = creditos;
        this.valorDoPlano = valorDoPlano;
        this.cpf = cpf;
        this.rg = rg;
        this.dataDeNascimento = dataDeNascimento;
        this.sexo = sexo;
        this.cep = cep;
        this.endereco = endereco;
        this.numero = numero;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.senha = senha;
        this.celular = celular;
    }

    // Método para consumir crédito
    public void consumirCredito() {
        // Implementação para marcar que o crédito foi consumido
        creditos--; // Por exemplo, apenas decrementa o número de créditos
    }
}
