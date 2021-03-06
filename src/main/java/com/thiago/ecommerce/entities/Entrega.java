package com.thiago.ecommerce.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_entregas")
public class Entrega implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco destino;

    @OneToOne
    @JoinColumn(name = "transportadora_id", referencedColumnName = "id")
    private Transportadora transportadora;

    private Double valor_entrega;

    public Entrega(){}

    public Entrega(Long id, Endereco endereco, Transportadora transportadora) {
        this.id = id;
        this.destino = endereco;
        this.transportadora = transportadora;
        this.valor_entrega = transportadora.getFrete();
    }

    public Endereco getEndereco() {
        return destino;
    }

    public void setEndereco(Endereco endereco) {
        this.destino = endereco;
    }

    public Transportadora getTransportadora() {
        return transportadora;
    }

    public void setTransportadora(Transportadora transportadora) {
        this.transportadora = transportadora;
    }

    public Double getValor_entrega() {
        return valor_entrega;
    }
}
