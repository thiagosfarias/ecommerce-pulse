package com.thiago.controledeestoque.entities;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Instant dataDePagamento;

    private String descricao;
    private Double valorTotalDePagamento;

    @OneToOne
    @JoinColumn(name = "tipoPagamento_id", referencedColumnName = "id")
    private TipoPagamento tipoPagamento;

    public Long getId() {
        return id;
    }

    public Instant getDataDePagamento() {
        return dataDePagamento;
    }

    public void setDataDePagamento(Instant dataDePagamento) {
        this.dataDePagamento = dataDePagamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorTotalDePagamento() {
        return valorTotalDePagamento;
    }

    public void setValorTotalDePagamento(Double valorTotalDePagamento) {
        this.valorTotalDePagamento = valorTotalDePagamento;
    }

    public TipoPagamento getTipoPagamento() {
        return tipoPagamento;
    }

    public void setTipoPagamento(TipoPagamento tipoPagamento) {
        this.tipoPagamento = tipoPagamento;
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
