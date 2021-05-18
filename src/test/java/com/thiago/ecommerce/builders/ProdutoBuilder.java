package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.Produto;

public class ProdutoBuilder {
    private Produto produto;

    public static ProdutoBuilder umProduto(){
        ProdutoBuilder builder = new ProdutoBuilder();

        builder.produto = new Produto(null, "Notebook Dell i5", "Notebook de ultima geracao", 7590.89);

        return builder;
    }

    public ProdutoBuilder comNome(String nome){
        this.produto.setNome(nome);
        return this;
    }
    public ProdutoBuilder comDescricao(String descricao){
        this.produto.setDescricao(descricao);
        return this;
    }
    public ProdutoBuilder comValor(Double valor){
        this.produto.setValor(valor);
        return this;
    }

    public Produto constroi(){
        return this.produto;
    }




}
