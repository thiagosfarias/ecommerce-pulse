package com.thiago.ecommerce.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "tb_item")
public class Item {
    private static final long serialUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    private Integer quantidade;

    private Double valor_item;

    public Item(){}

    public Item(Long id, Produto produto) {
        this.id = id;
        this.produto = produto;
        this.quantidade = 1;
        this.valor_item = produto.getValor();
    }

    public Long getId() {
        return id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
        setValorItem(quantidade);
    }

    public Double getValorItem() {
        return valor_item;
    }

    private void setValorItem(Integer quantidade) {
        this.valor_item = valor_item * quantidade;
    }
}
