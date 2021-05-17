package com.thiago.ecommerce.entities;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_cliente")
public class Cliente implements Serializable {
    private static final long serialUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique=true)
    private String nDocumento;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    private Instant dataNascimento;

    @OneToMany(mappedBy = "titular")
    private Set<Endereco> enderecos = new HashSet<>();

    @OneToMany
    @JoinTable(name = "tb_historicos_clientes",
            joinColumns = @JoinColumn(name="cliente_id"),
            inverseJoinColumns = @JoinColumn(name = "carrinho_id"))
    private Set<Carrinho> historicos = new HashSet<>();

    private String telefone;

    public Cliente(){}

    public Cliente(Long id, String nome, String nDocumento, Instant dataNascimento, String telefone) {
        this.id = id;
        this.nome = nome;
        this.nDocumento = nDocumento;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getnDocumento() {
        return nDocumento;
    }

    public void setnDocumento(String nDocumento) {
        this.nDocumento = nDocumento;
    }

    public Instant getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Instant dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @JsonIgnore
    public Set<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(Endereco endereco) {
        this.enderecos.add(endereco);
    }

    public Endereco getEndereco(Long id) {
        return this.enderecos.stream().filter(endereco -> endereco.getId() == id).findFirst().get();
    }

    @JsonIgnore
    public Set<Carrinho> getHistoricos() {
        return historicos;
    }

    public void setHistorico(Carrinho carrinho) {
        this.historicos.add(carrinho);
    }

    public void setHistoricos(Set<Carrinho> carrinhos){
        this.historicos = carrinhos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
