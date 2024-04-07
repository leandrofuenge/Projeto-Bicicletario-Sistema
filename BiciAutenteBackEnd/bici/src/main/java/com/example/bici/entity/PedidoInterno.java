package com.example.bici.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name = "PEDIDO_INTERNO")
public class PedidoInterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter @Setter
    @Column(name = "ESTADO_PEDIDO")
    private int estadoPedido;

    @Column(name = "DESCRICAO_PEDIDO")
    private String descricaoPedido;

    @Column(name = "DATA_HORA_RECEBIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraRecebido;

    @Column(name = "DATA_HORA_ACEITO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraAceito;

    @Column(name = "DATA_HORA_CONCLUIDO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraConcluido;

    // Construtor padrão necessário para JPA
    public PedidoInterno() {

    }

    // Construtor parametrizado para inicializar todos os campos
    public PedidoInterno(Long id, int estadoPedido, String descricaoPedido, Date dataHoraRecebido, Date dataHoraAceito, Date dataHoraConcluido) {
        this.id = id;
        this.estadoPedido = estadoPedido;
        this.descricaoPedido = descricaoPedido;
        this.dataHoraRecebido = dataHoraRecebido;
        this.dataHoraAceito = dataHoraAceito;
        this.dataHoraConcluido = dataHoraConcluido;
    }

    // Getters e setters omitidos por causa do Lombok
}