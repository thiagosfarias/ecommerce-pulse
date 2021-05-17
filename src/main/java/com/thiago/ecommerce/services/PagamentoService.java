package com.thiago.ecommerce.services;

import com.thiago.ecommerce.entities.Carrinho;
import com.thiago.ecommerce.entities.Pagamento;
import com.thiago.ecommerce.entities.enums.CarrinhoStatus;
import com.thiago.ecommerce.repositories.PagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository repository;
    @Autowired
    private CarrinhoService carrinhoService;

    public List<Pagamento> findAll(){
        return repository.findAll();
    }

    public Pagamento findById(Long id){
        Optional<Pagamento> obj = repository.findById(id);
        return obj.get();
    }

    public Pagamento insert(Pagamento obj){
        return repository.save(obj);
    }

    public Pagamento update(Pagamento obj){
        Pagamento oldPagamento = findById(obj.getId());

        oldPagamento.setConcluido(obj.getConcluido());

        return repository.save(oldPagamento);
    }

    public Pagamento process(Long idCarrinho) throws IllegalAccessException {
        Carrinho carrinho = carrinhoService.findById(idCarrinho);
        if(carrinho.getPagamento() != null){
            Pagamento pagamento = carrinho.getPagamento();

            carrinho.setStatus(CarrinhoStatus.PAID);
            pagamento.setConcluido(true);

            carrinhoService.update(carrinho);

            return update(pagamento);
        }
        return null;
    }

    public List<Pagamento> byClient(Long id){
        List<Pagamento> list = Optional.ofNullable( findAll() ).get();

        return list.stream()
                .filter(pagamento -> pagamento.getPagador().getId() == id)
                .collect(Collectors.toList());
    }


}
