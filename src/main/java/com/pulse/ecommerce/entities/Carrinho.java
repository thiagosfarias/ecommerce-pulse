package com.pulse.ecommerce.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "carrinho")
    private Set<Item> items = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "entrega_id", referencedColumnName = "id")
    private Entrega entrega;

    private Double valorTotal;

    public Carrinho(){}

    public Carrinho(Long id, Cliente cliente) {
        this.id = id;
        this.comprador = cliente;
    }

    public Long getId() {
        return id;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Item item) {
        this.items.add(item);
        this.valorTotal += item.getValor_produto();
    }

    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Cliente getComprador() {
        return comprador;
    }
}
