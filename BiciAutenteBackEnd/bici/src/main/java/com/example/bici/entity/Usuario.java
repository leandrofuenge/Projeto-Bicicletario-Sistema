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

    @Column()
    private String nomeCompleto;

    @Column(unique = true)
    private String numeroDoCartao;

    @Column()
    private Integer creditosRestantes;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String rg;

    @Column()
    private String dataDeNascimento;

    @Column()
    private String sexo;

    @Column()
    private String cep;

    @Column()
    private String endereco;

    @Column()
    private String numero;

    @Column()
    private String bairro;

    @Column()
    private String cidade;

    @Column()
    private String estado;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String numeroDeSerieBicicleta;

    @Column()
    private String corDaBicicleta;

    @Column()
    private String senha;

    @Column()
    private String celular;

    @Column()
    private String SimMomentaneoBicicletario;

    @Column()
    private String NaoMomentaneoBicicletario;

    public Usuario() {
        // Construtor padrão necessário para JPA
    }

    // Método para consumir crédito
    public void consumirCredito() {
        // Implementação para marcar que o crédito foi consumido
        if (creditosRestantes != null && creditosRestantes > 0) {
            creditosRestantes--; // Atualiza a quantidade restante de créditos
        }
    }

}
