package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.Carrinho;
import com.thiago.ecommerce.entities.Cliente;

public class CarrinhoBuilder {
    private Carrinho carrinho;

    public CarrinhoBuilder(){}

    public static CarrinhoBuilder umCarrinho(){
        CarrinhoBuilder builder = new CarrinhoBuilder();

        builder.carrinho = new Carrinho();

        return builder;
    }

    public CarrinhoBuilder comCliente(Cliente comprador){
        this.carrinho.setComprador(comprador);
        return this;
    }

    public Carrinho constroi(){
        return this.carrinho;
    }

}
