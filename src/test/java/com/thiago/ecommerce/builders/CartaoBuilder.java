package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.Cartao;

import java.time.Instant;

public class CartaoBuilder {
    private Cartao cartao;

    public CartaoBuilder(){}

    public static CartaoBuilder umCartao(){
        CartaoBuilder builder = new CartaoBuilder();

        builder.cartao = new Cartao(null, "13467985245", "THIAGO SOUSA FARIAS", "02676562380", Instant.parse("2027-12-25T20:30:50Z"));

        return builder;
    }

    public CartaoBuilder comNumero(String numero){
        this.cartao.setnCartao(numero);
        return this;
    }

    public CartaoBuilder comTitular(String titular){
        this.cartao.setTitular(titular);
        return this;
    }

    public CartaoBuilder comCpfDoTitular(String cpf){
        this.cartao.setCpfTitular(cpf);
        return this;
    }

    public CartaoBuilder comValidade(Instant validade){
        this.cartao.setValidade(validade);
        return this;
    }

    public Cartao constroi(){
        return this.cartao;
    }


}
