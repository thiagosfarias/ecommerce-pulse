package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.Endereco;
import com.thiago.ecommerce.entities.Entrega;
import com.thiago.ecommerce.entities.Transportadora;

public class EntregaBuilder {
    private Entrega entrega;

    public EntregaBuilder(){}

    public static EntregaBuilder umaEntrega(){
        EntregaBuilder builder = new EntregaBuilder();

        builder.entrega = new Entrega();

        return builder;
    }

    public EntregaBuilder comEndereco(Endereco endereco){
        this.entrega.setEndereco(endereco);
        return this;
    }

    public EntregaBuilder comTransportadora(Transportadora transportadora){
        this.entrega.setTransportadora(transportadora);
        return this;
    }

    public Entrega constroi(){
        return this.entrega;
    }

}
