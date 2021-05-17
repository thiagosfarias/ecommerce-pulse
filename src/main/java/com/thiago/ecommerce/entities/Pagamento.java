package com.thiago.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;
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

    @OneToOne
    @JoinColumn(name = "cupom_id")
    private Cupom cupom;

    private boolean hasCupom;

    @OneToOne
    @JoinTable(name = "tb_pagamentos_cartoes",
            joinColumns = @JoinColumn(name="pagamento_id"),
            inverseJoinColumns = @JoinColumn(name = "cartao_id"))
    private Cartao cartao;

    private boolean isConcluido;

    public Pagamento(){}

    public Pagamento(Long id, Carrinho carrinho, Entrega entrega, TipoPagamento tipoPagamento, boolean hasCupom) {
        this.id = id;
        this.dataDePagamento = Instant.now();
        this.carrinho = carrinho;
        this.entrega = entrega;
        setValorTotalDePagamento(carrinho, entrega);
        this.tipoPagamento = tipoPagamento;
        this.pagador = carrinho.getComprador();
        this.isConcluido = false;
        defineCupomReference(hasCupom);
    }

    public void defineCupomReference(boolean hasCupom){
        if(hasCupom)
            this.cupom = new Cupom();
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
        if(this.cupom != null && this.cupom.getValidade().isAfter(Instant.now()))
            this.valorTotalDePagamento = (carrinho.getSomaValoresItems() + entrega.getValor_entrega()) - this.cupom.getDesconto();
        else {
            this.valorTotalDePagamento = (carrinho.getSomaValoresItems() + entrega.getValor_entrega());
        }
    }

    public void divideValor(Integer parcelas){
        this.valorTotalDePagamento = this.valorTotalDePagamento/parcelas;
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

    public boolean getConcluido() {
        return isConcluido;
    }

    public void setConcluido(boolean concluido) {
        isConcluido = concluido;
    }

    public Cupom getCupom() {
        return cupom;
    }

    public void setCupom(Cupom cupom) {
        this.cupom = cupom;
        setValorTotalDePagamento(this.carrinho, this.entrega);
    }

    public Cartao getCartao() {
        return cartao;
    }

    public void setCartao(Cartao cartao) {
        this.cartao = cartao;
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
