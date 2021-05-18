package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.*;

public class PagamentoBuilder {
    private Pagamento pagamento;

    public PagamentoBuilder(){}

    public static PagamentoBuilder umPagamento(){
        PagamentoBuilder builder = new PagamentoBuilder();

        builder.pagamento = new Pagamento();

        return builder;
    }

    public PagamentoBuilder comUmPagador(Cliente pagador){
        this.pagamento.setPagador(pagador);
        return this;
    }

    public PagamentoBuilder comUmaEntrega(Entrega entrega){
        this.pagamento.setEntrega(entrega);
        return this;
    }

    public PagamentoBuilder comUmCarrinho(Carrinho carrinho){
        this.pagamento.setCarrinho(carrinho);
        return this;
    }

    public PagamentoBuilder comUmTipoDePagamento(TipoPagamento tipo){
        this.pagamento.setTipoPagamento(tipo);
        return this;
    }

    public PagamentoBuilder comUmCupom(boolean cupom){
        this.pagamento.setHasCupom(cupom);
        return this;
    }

    public Pagamento constroi() {
        return this.pagamento;
    }
}
