package com.thiago.ecommerce.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_cartao")
public class Cartao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nCartao;
    private String titular;
    private String cpfTitular;
    private Instant validade;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany
    @JoinTable(name = "tb_cartao_pagamentos", joinColumns = @JoinColumn(name = "cartao_id"), inverseJoinColumns = @JoinColumn(name = "pagamento_id"))
    List<Pagamento> pagamentos = new ArrayList<>();

    public Cartao(){}

    public Cartao(Long id, String nCartao, String titular, String cpfTitular, Instant validade) {
        this.id = id;
        this.nCartao = nCartao;
        this.titular = titular;
        this.cpfTitular = cpfTitular;
        this.validade = validade;
    }

    public Long getId() {
        return id;
    }

    public String getnCartao() {
        return nCartao;
    }

    public void setnCartao(String nCartao) {
        this.nCartao = nCartao;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCpfTitular() {
        return cpfTitular;
    }

    public void setCpfTitular(String cpfTitular) {
        this.cpfTitular = cpfTitular;
    }

    public Instant getValidade() {
        return validade;
    }

    public void setValidade(Instant validade) {
        this.validade = validade;
    }

    @JsonIgnore
    public List<Pagamento> getPagamentos() {
        return pagamentos;
    }

    public void setPagamentos(List<Pagamento> pagamentos) {
        this.pagamentos = pagamentos;
    }

    @JsonIgnore
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setPagamento(Pagamento pagamento){
        this.pagamentos.add(pagamento);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return Objects.equals(id, cartao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
