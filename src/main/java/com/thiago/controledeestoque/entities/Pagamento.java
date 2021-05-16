package com.thiago.controledeestoque.entities;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant dataDePagamento;

    private Double valorTotalDePagamento;

    @OneToOne
    @JoinColumn(name = "tipoPagamento_id", referencedColumnName = "id")
    private TipoPagamento tipoPagamento;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente pagador;

    @OneToOne
    @JoinColumn(name = "carrinho_id", referencedColumnName = "id")
    private Carrinho carrinho;

    @OneToOne
    @JoinColumn(name = "entrega_id", referencedColumnName = "id")
    private Entrega entrega;

    public Pagamento(){}

    public Pagamento(Long id, Carrinho carrinho, Entrega entrega, TipoPagamento tipoPagamento) {
        this.id = id;
        this.dataDePagamento = Instant.now();
        this.carrinho = carrinho;
        this.entrega = entrega;
        setValorTotalDePagamento(carrinho, entrega);
        this.tipoPagamento = tipoPagamento;
        this.pagador = carrinho.getComprador();
    }

    public Long getId() {
        return id;
    }

    public Instant getDataDePagamento() {
        return dataDePagamento;
    }

    public void setDataDePagamento(Instant dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public Double getValorTotalDePagamento() {
        return valorTotalDePagamento;
    }

    public void setValorTotalDePagamento(Carrinho carrinho, Entrega entrega) {
        this.valorTotalDePagamento = carrinho.getSomaValoresItems() + entrega.getValor_entrega();
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
    }

    @JsonIgnore
    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    @JsonIgnore
    public Entrega getEntrega() {
        return entrega;
    }

    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    public Cliente getPagador() {
        return pagador;
    }

    public void setPagador(Cliente pagador) {
        this.pagador = pagador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pagamento pagamento = (Pagamento) o;
        return Objects.equals(id, pagamento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
