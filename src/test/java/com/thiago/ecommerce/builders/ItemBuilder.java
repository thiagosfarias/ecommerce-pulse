package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.Item;
import com.thiago.ecommerce.entities.Produto;

public class ItemBuilder {
    private Item item;

    public ItemBuilder(){}

    public static ItemBuilder umItem(){
        ItemBuilder builder = new ItemBuilder();

        builder.item = new Item();

        return builder;
    }

    public ItemBuilder comProduto(Produto produto){
        this.item.setProduto(produto);
        return this;
    }

    public ItemBuilder comQuantidade(Integer quantidade){
        this.item.setQuantidade(quantidade);
        return this;
    }

    public Item constroi(){
        return this.item;
    }


}
