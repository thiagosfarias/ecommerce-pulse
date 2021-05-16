package com.thiago.controledeestoque.services;

import com.thiago.controledeestoque.entities.Pagamento;
import com.thiago.controledeestoque.entities.Produto;
import com.thiago.controledeestoque.repositories.PagamentoRepository;
import com.thiago.controledeestoque.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoService {
    @Autowired
    private PagamentoRepository repository;

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
        return null;
    }
}
