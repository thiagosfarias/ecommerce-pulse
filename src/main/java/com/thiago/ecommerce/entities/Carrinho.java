package com.thiago.ecommerce.entities;

import com.thiago.ecommerce.entities.enums.CarrinhoStatus;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "item_id")
    private List<Item> items = new ArrayList<>();

    private Instant dataDeCriacao;

    private Integer status;

    @OneToOne
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private Pagamento pagamento;

    public Carrinho(){}

    public Carrinho(Long id, Cliente cliente) {
        this.id = id;
        this.comprador = cliente;
        this.dataDeCriacao = Instant.now();
        setStatus(CarrinhoStatus.NEW);
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

    public CarrinhoStatus getStatus() throws IllegalAccessException {
        return CarrinhoStatus.valueOf(this.status);
    }

    public void setStatus(CarrinhoStatus status) {
        if(status!= null)
            this.status = status.getCode();
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    public Double getSomaValoresItems(){
        return this.items
                    .stream()
                    .map(Item::getValorItem)
                    .reduce(0.0, Double::sum);
    }

}
