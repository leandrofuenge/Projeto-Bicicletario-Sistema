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

    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Column(name = "numero_do_cartao", unique = true)
    private String numeroDoCartao;

    @Column(name = "creditos_restantes")
    private Integer creditosRestantes;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String rg;

    @Column(name = "data_de_nascimento")
    private String dataDeNascimento;

    private String sexo;

    private String cep;

    private String endereco;

    private String numero;

    private String bairro;

    private String cidade;

    private String estado;

    @Column(unique = true)
    private String email;

    @Column(name = "numero_de_serie_bicicleta", unique = true)
    private String numeroDeSerieBicicleta;

    @Column(name = "cor_da_bicicleta")
    private String corDaBicicleta;

    private String senha;

    private String celular;

    public Usuario() {
        // Construtor padrão necessário para JPA
    }

    public Usuario(Long id, String nomeCompleto, String numeroDoCartao, Integer creditosRestantes,
                   String cpf, String rg, String dataDeNascimento, String sexo, String cep, String endereco,
                   String numero, String bairro, String cidade, String estado, String email,
                   String numeroDeSerieBicicleta, String corDaBicicleta, String senha,
                   String celular) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.numeroDoCartao = numeroDoCartao;
        this.creditosRestantes = creditosRestantes;
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
        this.email = email;
        this.numeroDeSerieBicicleta = numeroDeSerieBicicleta;
        this.corDaBicicleta = corDaBicicleta;
        this.senha = senha;
        this.celular = celular;
    }
}








