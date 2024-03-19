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
    private String nomeCompleto;

    @Column(nullable = false, unique = true)
    private String numeroDoCartao;

    @Column(nullable = false)
    private int creditosRestantes;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String rg;

    @Column(nullable = false)
    private String dataDeNascimento;

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
    private String celular;

    public Usuario() {
        // Construtor padrão necessário para JPA
    }

    // Método para consumir crédito
    public void consumirCredito() {
        // Implementação para marcar que o crédito foi consumido
        creditosRestantes--; // Atualiza a quantidade restante de créditos
    }

    public int getCreditos() {
        return 100; // Implemente a lógica para obter os créditos do usuário, se necessário
    }
}
