package com.thiago.ecommerce.services;

import com.thiago.ecommerce.builders.*;
import com.thiago.ecommerce.entities.*;
import com.thiago.ecommerce.repositories.CarrinhoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CompraCarrinhoTest {
    @Autowired
    private CarrinhoService service;


    @Test
    void deveRemoverUmItemDoCarrinho(){
        Cliente cliente = ClienteBuilder.umCliente().constroi();
        Carrinho carrinho = CarrinhoBuilder.umCarrinho().comCliente(cliente).constroi();
        Produto produto = ProdutoBuilder.umProduto().constroi();
        Item item = ItemBuilder.umItem().comProduto(produto).constroi();





    }

    @Test
    void deveInserirUmNovoItemNoCarrinho(){

    }

    @Test
    void deveEfetuarPagamento(){

    }

    @Test
    void deveGarantirQueOCupomSoSeAplicaSeEstiverValido(){
        Cupom cupom = CupomBuilder.umCupom().constroi();


    }


    @Test
    void naoDevePagarSemHaverUmaEntrega(){

    }

    @Test
    void deveCancelarUmaCompra(){

    }
}
