package com.thiago.controledeestoque.services;

import com.thiago.controledeestoque.repositories.ProdutoRepository;
import com.thiago.controledeestoque.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    public List<Produto> findAll(){
        return repository.findAll();
    }

    public Produto findById(Long id){
        Optional<Produto> obj = repository.findById(id);
        return obj.get();
    }

    public Produto insert(Produto obj){
        return repository.save(obj);
    }
}