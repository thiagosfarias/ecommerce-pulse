package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.Cliente;
import com.thiago.ecommerce.entities.Endereco;

import java.time.Instant;

public class ClienteBuilder {
    private Cliente cliente;

    public ClienteBuilder (){}

    public static ClienteBuilder umCliente(){
        ClienteBuilder builder = new ClienteBuilder();
        builder.cliente = new Cliente(1L, "Thiago","0245784525", Instant.parse("1994-07-08T19:53:07Z"), "982733812");

        return builder;
    }

     public ClienteBuilder comUmEndereco(Endereco endereco){
        this.cliente.setEnderecos(endereco);
        return this;
     }

     public Cliente constroi(){
        return this.cliente;
     }




}
