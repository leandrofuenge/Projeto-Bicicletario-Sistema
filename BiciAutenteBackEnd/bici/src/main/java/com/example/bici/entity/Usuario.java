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

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String rg;

    @Column(name = "data_de_nascimento")
    private String dataDeNascimento;

    @Column(name = "numero_do_cartao", unique = true)
    private String numeroDoCartao;

    @Column(name = "creditos_restantes")
    private Integer creditosRestantes;

    @Column(unique = true)
    private String email;

    @Column(name = "numero_de_serie_bicicleta", unique = true)
    private String numeroDeSerieBicicleta;

    @Column(name = "cor_da_bicicleta")
    private String corDaBicicleta;

    @Column(name = "bloqueado_desbloqueado")
    private String bloqueadoDesbloqueado;

    @Column
    private String liberado;

    @Column
    private String senha;





    @Column
    private String Sexo;

    @Column
    private String Cep;

    @Column
    private String Endereco;

    @Column
    private String Numero;

    @Column
    private String Bairro;

    @Column
    private String Cidade;

    @Column
    private String Estado;

    @Column
    private String Celular;

    // Constructor
    public Usuario() {
        // Construtor padrão necessário para JPA
    }

    public Usuario(Long id, String nomeCompleto, String numeroDoCartao, Integer creditosRestantes,
                   String cpf, String rg, String dataDeNascimento, String email,
                   String numeroDeSerieBicicleta, String corDaBicicleta, String senha, String Sexo, String Cep,
                   String Endereco, String Numero, String Bairro, String Cidade, String Estado, String Celular) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.numeroDoCartao = numeroDoCartao;
        this.creditosRestantes = creditosRestantes;
        this.cpf = cpf;
        this.rg = rg;
        this.dataDeNascimento = dataDeNascimento;
        this.email = email;
        this.numeroDeSerieBicicleta = numeroDeSerieBicicleta;
        this.corDaBicicleta = corDaBicicleta;
        this.senha = senha;
        this.Sexo = Sexo;
        this.Cep = Cep;
        this.Endereco = Endereco;
        this.Numero = Numero;
        this.Bairro = Bairro;
        this.Cidade = Cidade;
        this.Estado = Estado;
        this.Celular = Celular;
    }

    // Getters e Setters omitidos para brevidade

    // Outros métodos que estavam presentes, mas não parecem relevantes para o contexto, foram removidos.
}
