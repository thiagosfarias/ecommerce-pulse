package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.Transportadora;

public class TransportadoraBuilder {
    private Transportadora transportadora;

    public TransportadoraBuilder(){}

    public static TransportadoraBuilder umaTransportadora(){
        TransportadoraBuilder builder = new TransportadoraBuilder();

        builder.transportadora =  new Transportadora(null, "SL Delivery", "0264568978415", 75.50);

        return builder;
    }

    public TransportadoraBuilder comNome(String nome){
        this.transportadora.setNome(nome);
        return this;
    }

    public TransportadoraBuilder comDocumento(String documento){
        this.transportadora.setDocumento(documento);
        return this;
    }

    public TransportadoraBuilder comFrete(Double frete){
        this.transportadora.setFrete(frete);
        return this;
    }

    public Transportadora constroi(){
        return this.transportadora;
    }
}
