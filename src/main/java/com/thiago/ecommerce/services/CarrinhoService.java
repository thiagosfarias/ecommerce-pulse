package com.thiago.ecommerce.services;

import com.thiago.ecommerce.entities.*;
import com.thiago.ecommerce.entities.enums.CarrinhoStatus;
import com.thiago.ecommerce.repositories.CarrinhoRepository;
import org.aspectj.weaver.patterns.OrTypePattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

    public Carrinho pay(Long id, TipoPagamento tipoPagamento){
        Optional<Carrinho> objCarrinho = Optional.ofNullable(repository.findById(id)).get();
        Optional<TipoPagamento> objTipoPagamento = Optional.ofNullable(tipoPagamentoService.insert(tipoPagamento));

        if(objCarrinho.isPresent() && objCarrinho.get().getEntrega() != null){
            objCarrinho.get().setPagamento( Optional.ofNullable( pagamentoService.insert(
                                            new Pagamento(null,
                                                            objCarrinho.get(),
                                                            objCarrinho.get().getEntrega(),
                                                            objTipoPagamento.get())) ).get());

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
