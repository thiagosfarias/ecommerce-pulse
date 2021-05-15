package com.thiago.controledeestoque.services;

import com.thiago.controledeestoque.entities.Carrinho;
import com.thiago.controledeestoque.repositories.CarrinhoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository repository;

    public List<Carrinho> findAll(){
        return repository.findAll();
    }

    public Carrinho findById(Long id){
        Optional<Carrinho> obj = repository.findById(id);
        return obj.get();
    }

    public Carrinho insert(Carrinho obj){
        return repository.save(obj);
    }
}