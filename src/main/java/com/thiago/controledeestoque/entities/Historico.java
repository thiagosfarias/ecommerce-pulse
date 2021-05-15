package com.thiago.controledeestoque.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "tb_historico")
public class Historico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "pagamento_id", referencedColumnName = "id")
    private Pagamento pagamento;
    private Cliente pagador;
    private Double valorEntrega;
    private Double valorProdutos;


}
