package com.example.bici.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomecompleto;

    private String numeroDoCartao;

    private int creditos;

    private String valorDoPlano;

    @Column(nullable = false)
    private String cpf;

    @Column(nullable = false)
    private String rg;

    @Column(nullable = false)
    private String datadenascimento;

    @Column(nullable = false)
    private String sexo;

    @Column(nullable = false)
    private String cep;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String numero;

    @Column(nullable = false)
    private String bairro;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String estado;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column(nullable = false)
    private String celular; // Corrigido para começar com letra minúscula

    public Usuario() {
        // Construtor padrão necessário para JPA
    }

    public Usuario(String nomecompleto, String email, String numeroDoCartao, int creditos, String valorDoPlano,
                   String cpf, String rg, String datadenascimento, String sexo, String cep, String endereco,
                   String numero, String bairro, String cidade, String estado, String senha, String celular) {
        this.nomecompleto = nomecompleto;
        this.email = email;
        this.numeroDoCartao = numeroDoCartao;
        this.creditos = creditos;
        this.valorDoPlano = valorDoPlano;
        this.cpf = cpf;
        this.rg = rg;
        this.datadenascimento = datadenascimento;
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

    public void consumirCredito() {
        // Implementação para marcar que o crédito foi consumido
        creditos--; // Por exemplo, apenas decrementa o número de créditos
    }

    public Object getNome() {
        return null;
    }
}
