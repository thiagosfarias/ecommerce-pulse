package com.thiago.ecommerce.builders;

import com.thiago.ecommerce.entities.Cliente;
import com.thiago.ecommerce.entities.Endereco;

public class EnderecoBuilder {
    private Endereco endereco;

    public EnderecoBuilder (){}

    public static EnderecoBuilder umEndereco(){
        EnderecoBuilder builder = new EnderecoBuilder();
        builder.endereco = new Endereco(null, "Av da Paz", "65072570","Parque Shalom", "Sao Luis", "Maranhao");

        return builder;
    }

    public EnderecoBuilder comUmTitular(Cliente titular){
        this.endereco.setTitular(titular);
        return this;
    }

    public EnderecoBuilder comUmaRua(String rua){
        this.endereco.setRua(rua);
        return this;
    }

    public EnderecoBuilder comUmCEP(String cep){
        this.endereco.setCep(cep);
        return this;
    }

    public EnderecoBuilder comUmBairro(String bairro){
        this.endereco.setBairro(bairro);
        return this;
    }

    public EnderecoBuilder comUmaCidade(String cidade){
        this.endereco.setCidade(cidade);
        return this;
    }

    public EnderecoBuilder comUmEstado(String estado){
        this.endereco.setEstado(estado);
        return this;
    }


    public Endereco constroi(){
        return this.endereco;
    }
}
