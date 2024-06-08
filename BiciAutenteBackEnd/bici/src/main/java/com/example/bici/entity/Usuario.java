package com.example.bici.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "USUARIO")
public class Usuario {

    // Getters and Setters
    @Setter
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @Setter
    @Getter
    @Column(unique = true)
    private String cpf;

    @Setter
    @Getter
    @Column(unique = true)
    private String rg;

    @Setter
    @Getter
    @Column(name = "data_de_nascimento")
    private String dataDeNascimento;

    @Setter
    @Getter
    @Column(name = "numero_do_cartao", unique = true)
    private String numeroDoCartao;

    @Getter
    @Setter
    @Column(name = "creditos_restantes")
    private Integer creditosRestantes;

    @Setter
    @Getter
    @Column(unique = true)
    private String email;

    @Setter
    @Getter
    @Column(name = "numero_de_serie_bicicleta", unique = true)
    private String numeroDeSerieBicicleta;

    @Getter
    @Setter
    @Column(name = "cor_da_bicicleta")
    private String corDaBicicleta;

    @Setter
    @Column(name = "BLOQUEADO_DESBLOQUEADO")
    private String bloqueadoDesbloqueado;

    @Setter
    @Column(name = "liberado")
    private String liberado;

    // Novo atributo senha
    @Column
    private String senha;

    public Usuario() {
        // Construtor padrão necessário para JPA
    }

    public Usuario(Long id, String nomeCompleto, String numeroDoCartao, Integer creditosRestantes,
                   String cpf, String rg, String dataDeNascimento, String email,
                   String numeroDeSerieBicicleta, String corDaBicicleta, String senha) {
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
    }

    // Getters e Setters
    // Os métodos getters e setters para o atributo senha já foram gerados automaticamente pelo Lombok



    public String getSexo() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCep() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEndereco() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getNumero() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCidade() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getBairro() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getEstado() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getSenha() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCelular() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setSexo(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setCep(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setEndereco(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setNumero(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setBairro(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setCidade(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setEstado(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setSenha(String string) {
		// TODO Auto-generated method stub
		
	}

	public void setCelular(String string) {
		// TODO Auto-generated method stub
		
	}
}
