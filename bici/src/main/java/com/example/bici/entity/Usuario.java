package com.example.bici.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "USUARIO")
public class Usuario {

    // Getters e setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String numeroDoCartao;
    private int creditos;
    private String valorDoPlano;

    public Usuario() {
        // Construtor padrão necessário para JPA
    }

    public Usuario(String nome, String email, String numeroDoCartao, int creditos, String valorDoPlano) {
        this.nome = nome;
        this.email = email;
        this.numeroDoCartao = numeroDoCartao;
        this.creditos = creditos;
        this.valorDoPlano = valorDoPlano;
    }

    public void consumirCredito() {
        // Implementação para marcar que o crédito foi consumido
        creditos--; // Por exemplo, apenas decrementa o número de créditos
    }

}