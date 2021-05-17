package com.thiago.ecommerce.services;

import com.thiago.ecommerce.entities.*;
import com.thiago.ecommerce.entities.enums.CarrinhoStatus;
import com.thiago.ecommerce.entities.enums.Pagamentos;
import com.thiago.ecommerce.repositories.CarrinhoRepository;
import org.aspectj.weaver.patterns.OrTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.sql.Time;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository repository;
    @Autowired
    private ItemService itemService;
    @Autowired
    private ProdutoService produtoService;
    @Autowired
    private EntregaService entregaService;
    @Autowired
    private PagamentoService pagamentoService;
    @Autowired
    private TipoPagamentoService tipoPagamentoService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CupomService cupomService;
    @Autowired
    private CartaoService cartaoService;

    public List<Carrinho> findAll(){
        return repository.findAll();
    }

    public Carrinho findById(Long id){
        Optional<Carrinho> carrinho = Optional.of( repository.findById(id) ).get();
        if(carrinho.isPresent()){
            return carrinho.get();
        }
        return null;
    }

    public Carrinho insert(Carrinho obj){
        return repository.save(obj);
    }

    public Carrinho novoCarrinho(Long id){
        Cliente cliente = clienteService.findById(id);

        Carrinho carrinho = new Carrinho(null, cliente);

        cliente.setHistorico(carrinho);

        repository.save(carrinho);

        clienteService.update(cliente, id);

        return carrinho;
    }

    public Carrinho deleteItem(Long id, int index){
        Optional<Carrinho> obj = Optional.ofNullable( findById(id) );

        if(obj.isPresent()){
            Carrinho carrinho = obj.get();
            carrinho.getItems().remove(index);
            repository.save(carrinho);
            return carrinho;
        }

        return null;
    }

    public Carrinho novoItem(Long id, Long idProduto, int quantidade){
        try{
            Optional<Carrinho> obj = Optional.ofNullable(findById(id));
            Optional<Produto> produto = Optional.ofNullable( produtoService.findById(idProduto) );

            if(obj.isPresent() && produto.isPresent()){
                Item item = new Item(null, produto.get());
                item.setQuantidade(quantidade);
                itemService.insert(item);
                obj.get().setItems(item);
                return repository.save(obj.get());
            }

            return null;
        } catch (NoSuchElementException exception){
            return null;
        }



    }

    public Carrinho checkout(Long id){
        Optional<Carrinho> carrinho = Optional.ofNullable(findById(id));

        if(carrinho.isPresent() && !carrinho.get().getItems().isEmpty()){
            carrinho.get().setStatus(CarrinhoStatus.WAITING_PAYMENT);
            return repository.save(carrinho.get());
        }

        return null;
    }

    public void cancel(Long id){
        Optional<Carrinho> obj = Optional.ofNullable(findById(id));

        if(obj.isPresent()){
            repository.delete(obj.get());
        }
    }

    public Carrinho defineShippment(Long id, Long idEndereco, Long idTrasnportadora){
        Optional<Entrega> entrega = Optional.ofNullable(entregaService.insert(idEndereco, idTrasnportadora));
        Optional<Carrinho> objCarrinho = Optional.ofNullable(repository.findById(id)).get();

        if(entrega.isPresent() && objCarrinho.isPresent() && objCarrinho.get().getItems() != null){
            objCarrinho.get().setEntrega(entrega.get());
            return repository.save(objCarrinho.get());
        }

        return null;
    }

    public Carrinho payWithCard(Pagamento pagamento,
                                Cliente cliente,
                                Cartao obj,
                                Carrinho objCarrinho,
                                Cupom cupom,
                                Integer parcelas){

        pagamento.setCartao(obj);

        cliente.setCartao(obj);

        obj.setCliente(cliente);

        obj.setPagamento(pagamento);

        pagamento.setCupom(cupom);

        pagamento.divideValor(parcelas);

        objCarrinho.setPagamento((pagamentoService.insert(pagamento)));

        objCarrinho.setStatus(CarrinhoStatus.PROCESSING);

        clienteService.update(cliente, cliente.getId());

        cartaoService.update(obj);

        return repository.save(objCarrinho);
    }

    public Carrinho payWithCardNoCupom(Pagamento pagamento,
                                       Cliente cliente,
                                       Cartao obj,
                                       Carrinho carrinho,
                                       Integer parcelas){
        pagamento.setCartao(obj);

        cliente.setCartao(obj);

        obj.setCliente(cliente);

        obj.setPagamento(pagamento);

        pagamento.divideValor(parcelas);

        carrinho.setPagamento((pagamentoService.insert(pagamento)));

        carrinho.setStatus(CarrinhoStatus.PROCESSING);

        clienteService.update(cliente, cliente.getId());

        cartaoService.update(obj);

        return repository.save(carrinho);

    }

    public Carrinho pay(Long id, Integer tipoPagamento, Integer parcelas, Cartao obj) throws IllegalAccessException {
        Optional<Carrinho> objCarrinho = Optional.ofNullable(repository.findById(id)).get();

        TipoPagamento tipo = new TipoPagamento(null, Pagamentos.valueOf(tipoPagamento));

        Cliente cliente = clienteService.findById(objCarrinho.get().getComprador().getId());

        if (objCarrinho.isPresent() && objCarrinho.get().getEntrega() != null && cliente != null) {
            tipoPagamentoService.insert(tipo);

            Pagamento pagamento = new Pagamento(null, objCarrinho.get(), objCarrinho.get().getEntrega(), tipo, false);

            if (tipo.getTipo().getCode() == 1) {
                obj = cartaoService.novo(obj);
                return payWithCardNoCupom(pagamento, cliente, obj, objCarrinho.get(), parcelas);
            }

            objCarrinho.get().setPagamento(pagamento);

            objCarrinho.get().setStatus(CarrinhoStatus.PROCESSING);

            Optional.ofNullable(pagamentoService.insert(pagamento));

            return repository.save(objCarrinho.get());

        }

        return null;
    }


    public Carrinho payWithCupom(Long id, Integer tipoPagamento, Long idCupom, Integer parcelas, Cartao obj) throws IllegalAccessException {
        Optional<Carrinho> objCarrinho = Optional.ofNullable(repository.findById(id)).get();

        TipoPagamento tipo = new TipoPagamento(null, Pagamentos.valueOf(tipoPagamento));

        Cliente cliente = clienteService.findById(objCarrinho.get().getComprador().getId());

        if(objCarrinho.isPresent() && objCarrinho.get().getEntrega() != null && cliente != null){
            tipoPagamentoService.insert(tipo);

            Pagamento pagamento = new Pagamento(null, objCarrinho.get(), objCarrinho.get().getEntrega(), tipo, true);

            Cupom cupom = cupomService.findById(idCupom);

            if(cupom == null || cliente == null) return null;

            obj = cartaoService.novo(obj);

            if(tipo.getTipo().getCode() == 1){
                return payWithCard(pagamento, cliente, obj, objCarrinho.get(), cupom, parcelas);
            }

            pagamento.setCupom(cupom);

            objCarrinho.get().setPagamento( Optional.ofNullable(pagamentoService.insert(pagamento)).get());

            objCarrinho.get().setStatus(CarrinhoStatus.PROCESSING);

            return repository.save(objCarrinho.get());
        }

        return null;
    }

    public Carrinho update(Carrinho obj) throws IllegalAccessException {
        if(obj != null){
            Carrinho old = findById(obj.getId());
            old.setStatus(obj.getStatus());
            return repository.save(obj);
        }
        return null;
    }
}





