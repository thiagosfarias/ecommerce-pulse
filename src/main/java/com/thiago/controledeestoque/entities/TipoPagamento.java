package com.thiago.controledeestoque.entities;

import com.thiago.controledeestoque.entities.enums.Pagamentos;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_tipoPagamento")
public class TipoPagamento implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Pagamentos tipo;

    @OneToOne
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private Pagamento pagamento;

    public TipoPagamento(Long id, Pagamentos tipo, Pagamento pagamento) {
        this.id = id;
        this.tipo = tipo;
        this.pagamento = pagamento;
    }


}
