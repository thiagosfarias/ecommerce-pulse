package com.thiago.ecommerce.config;

import com.thiago.ecommerce.entities.*;
import com.thiago.ecommerce.entities.enums.CarrinhoStatus;
import com.thiago.ecommerce.entities.enums.Pagamentos;
import com.thiago.ecommerce.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.util.Arrays;

@Configuration
public class TestConfig implements CommandLineRunner {

    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private TransportadoraRepository transportadoraRepository;
    @Autowired
    private EntregaRepository entregaRepository;
    @Autowired
    private CarrinhoRepository carrinhoRepository;
    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;
    @Autowired
    private PagamentoRepository pagamentoRepository;
    @Autowired
    private CartaoRepository cartaoRepository;
    @Autowired
    private CupomRepository cupomRepository;

    @Override
    public void run(String... args) throws Exception {

        Endereco e1 = new Endereco(null, "Av da Paz", "65072570","Parque Shalom", "Sao Luis", "Maranhao");
        Endereco e2 = new Endereco(null, "Av Um", "65072000","Residencial Pinheiros", "Sao Luis", "Maranhao");

        enderecoRepository.saveAll(Arrays.asList(e1,e2));

        Cliente c1 = new Cliente(null, "Thiago","0245784525", Instant.parse("1994-07-08T19:53:07Z"), "982733812");
        Cliente c2 = new Cliente(null, "Talyson","024578469", Instant.parse("1995-07-08T19:53:07Z"), "982733852");

        clienteRepository.saveAll(Arrays.asList(c1, c2));

        Transportadora t1 = new Transportadora(null, "SL Delivery", "0264568978415", 75.50);
        Transportadora t2 = new Transportadora(null, "XAE Transport", "0264554978415", 70.00);

        transportadoraRepository.saveAll(Arrays.asList(t1,t2));

        c1.setEnderecos(e1);
        c1.setEnderecos(e2);

        e1.setTitular(c1);
        e2.setTitular(c1);

        clienteRepository.save(c1);
        enderecoRepository.saveAll(Arrays.asList(e1,e2));

        Produto p1 = new Produto(null, "Notebook Dell i5", "Notebook de ultima geracao", 7590.89);
        Produto p2 = new Produto(null, "Notebook Dell i7", "Notebook de ultima geracao", 8590.89);
        Produto p3 = new Produto(null, "Notebook Dell i9", "Notebook de ultima geracao", 10590.89);

        produtoRepository.saveAll(Arrays.asList(p1,p2,p3));

        Item it1 = new Item(null, p1);
        Item it2 = new Item(null, p1);

        itemRepository.saveAll(Arrays.asList(it1,it2));

        it1.setQuantidade(2);

        itemRepository.save(it1);

        Carrinho ca1 = new Carrinho(null, c1);

        carrinhoRepository.save(ca1);

        c1.setHistorico(ca1);

        clienteRepository.save(c1);

        ca1.setItems(it1);
        ca1.setItems(it2);

        Cartao card1 = new Cartao(null, "13467985245", "THIAGO SOUSA FARIAS", "02676562380", Instant.parse("2027-12-25T20:30:50Z"));

        Cupom cup1 = new Cupom(null, "CUPOM100", 100.00, Instant.parse("2021-12-25T20:30:50Z"));

        cupomRepository.save(cup1);
        cartaoRepository.save(card1);

        TipoPagamento tp1 = new TipoPagamento(null, Pagamentos.CARTAO);

        itemRepository.saveAll(Arrays.asList(it1,it2));
        carrinhoRepository.save(ca1);
        tipoPagamentoRepository.save(tp1);


    }
}