package com.thiago.ecommerce.entities;

import com.thiago.ecommerce.entities.enums.Pagamentos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_tipoPagamento")
public class TipoPagamento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tipo;

    public TipoPagamento(){}

    public TipoPagamento(Long id, Pagamentos tipo) {
        this.id = id;
        setTipo(tipo);
    }

    public Long getId() {
        return id;
    }

    public Pagamentos getTipo() throws IllegalAccessException {
        return Pagamentos.valueOf(this.tipo);
    }

    public void setTipo(Pagamentos tipo) {
        if(tipo != null)
            this.tipo = tipo.getCode();
    }
}
