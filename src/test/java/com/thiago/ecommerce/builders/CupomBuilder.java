package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.Cupom;

import java.time.Instant;

public class CupomBuilder {
    private Cupom cupom;

    public CupomBuilder(){}

    public static CupomBuilder umCupom(){
        CupomBuilder builder = new CupomBuilder();
        builder.cupom = new Cupom(null, "CUPOM100", 100.00, Instant.parse("2021-12-25T20:30:50Z"));

        return builder;
    }

    public CupomBuilder comCodigo(String codigo){
        this.cupom.setCodigo(codigo);
        return this;
    }

    public CupomBuilder comDesconto(Double desconto){
        this.cupom.setDesconto(desconto);
        return this;
    }

    public CupomBuilder comValiade(Instant validade){
        this.cupom.setValidade(validade);
        return this;
    }

    public Cupom constroi(){
        return this.cupom;
    }
}
