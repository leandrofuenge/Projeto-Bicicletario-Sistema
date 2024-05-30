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

    @Column(name = "BLOQUEADO_DESBLOQUEADO")
    private String bloqueadoDesbloqueado;

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

    
    
    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getDataDeNascimento() {
        return dataDeNascimento;
    }

    public void setDataDeNascimento(String dataDeNascimento) {
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNumeroDoCartao() {
        return numeroDoCartao;
    }

    public void setNumeroDoCartao(String numeroDoCartao) {
        this.numeroDoCartao = numeroDoCartao;
    }

    public Integer getCreditosRestantes() {
        return creditosRestantes;
    }

    public void setCreditosRestantes(Integer creditosRestantes) {
        this.creditosRestantes = creditosRestantes;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroDeSerieBicicleta() {
        return numeroDeSerieBicicleta;
    }

    public void setNumeroDeSerieBicicleta(String numeroDeSerieBicicleta) {
        this.numeroDeSerieBicicleta = numeroDeSerieBicicleta;
    }

    public String getCorDaBicicleta() {
        return corDaBicicleta;
    }

    public void setCorDaBicicleta(String corDaBicicleta) {
        this.corDaBicicleta = corDaBicicleta;
    }

    public String getBloqueadoDesbloqueado() {
        return bloqueadoDesbloqueado;
    }

    public void setBloqueadoDesbloqueado(String bloqueadoDesbloqueado) {
        this.bloqueadoDesbloqueado = bloqueadoDesbloqueado;
    }

    public String getLiberado() {
        return liberado;
    }

    public void setLiberado(String liberado) {
        this.liberado = liberado;
    }

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
