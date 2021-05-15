package com.thiago.controledeestoque.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_carrinho")
public class Carrinho implements Serializable {
    private static final long serialUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Cliente comprador;

    @OneToOne
    @JoinColumn(name = "entrega_id", referencedColumnName = "id")
    private Entrega entrega;

    @OneToMany(mappedBy = "carrinho")
    private List<Item> items = new ArrayList<>();

    private Instant dataDeCriacao;

    public Carrinho(){}

    public Carrinho(Long id, Cliente cliente) {
        this.id = id;
        this.comprador = cliente;
        this.dataDeCriacao = Instant.now();
    }

    public Long getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(Item item) {
        this.items.add(item);
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Cliente getComprador() {
        return comprador;
    }

    public Instant getDataDeCriacao() {
        return dataDeCriacao;
    }
}
