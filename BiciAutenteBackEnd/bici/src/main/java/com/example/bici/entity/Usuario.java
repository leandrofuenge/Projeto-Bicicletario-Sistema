package com.example.bici.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // Anotação para indicar que esta classe é uma entidade JPA
@Table(name = "USUARIO") // Especifica o nome da tabela no banco de dados
public class Usuario {

    @Id // Anotação para indicar que este campo é a chave primária
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Configura a estratégia de geração de valores para a chave primária
    private Long id; // Identificador único do usuário

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    // Documentos

    @Column(name = "nome_completo") // Mapeia o nome da coluna no banco de dados
    private String nomeCompleto; // Nome completo do usuário

    @Column(unique = true) // Mapeia o CPF com a opção de ser único
    private String cpf; // Número do CPF do usuário

    @Column(unique = true) // Mapeia o RG com a opção de ser único
    private String rg; // Número do RG do usuário

    @Column(name = "data_de_nascimento") // Mapeia a data de nascimento
    private String dataDeNascimento; // Data de nascimento do usuário

    

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    // Dados Do Cartão

    @Column(name = "numero_do_cartao", unique = true) // Mapeia o número do cartão com a opção de ser único
    private String numeroDoCartao; // Número do cartão do usuário

    @Column(name = "creditos_restantes") // Mapeia o número de créditos restantes
    private Integer creditosRestantes; // Créditos restantes do usuário

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    // Endereço Completo

    

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    // Contatos

    @Column(unique = true) // Mapeia o e-mail com a opção de ser único
    private String email; // E-mail do usuário

    

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    // Dados Da Bicicleta

    @Column(name = "numero_de_serie_bicicleta", unique = true) // Mapeia o número de série da bicicleta com a opção de ser único
    private String numeroDeSerieBicicleta; // Número de série da bicicleta associada ao usuário

    @Column(name = "cor_da_bicicleta") // Mapeia a cor da bicicleta
    private String corDaBicicleta; // Cor da bicicleta associada ao usuário

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    // Senha de Login

    

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    @Column(name = "BLOQUEADO_DESBLOQUEADO") // Mapeia o status do cartão
    private String bloqueadoDesbloqueado; // Status do cartão do usuário

    @Column(name = "liberado") // Mapeia o status de liberação do cartão
    private String liberado; // Status de liberação do cartão do usuário

    //------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------//

    public Usuario() {
        // Construtor padrão necessário para JPA
    }

    // Construtor parametrizado para inicializar todos os campos
    public Usuario(Long id, String nomeCompleto, String numeroDoCartao, Integer creditosRestantes,
                   String cpf, String rg, String dataDeNascimento, String sexo, String cep, String endereco,
                   String numero, String bairro, String cidade, String estado, String email,
                   String numeroDeSerieBicicleta, String corDaBicicleta, String senha, String celular) {
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
    }

    // Método para definir o ID do usuário
    public void setId(Long id) {
        this.id = id;
    }

	public Object getNomeCompleto() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setNomeCompleto(Object nomeCompleto2) {
		// TODO Auto-generated method stub
		
	}

	public Object getEmail() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setEmail(Object email2) {
		// TODO Auto-generated method stub
		
	}
}
