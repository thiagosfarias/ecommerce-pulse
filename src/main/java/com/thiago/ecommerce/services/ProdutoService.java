package com.thiago.ecommerce.services;

import com.thiago.ecommerce.repositories.ProdutoRepository;
import com.thiago.ecommerce.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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
        return Optional.ofNullable(repository.findById(id)).get().orElseThrow();
    }

    public Produto insert(Produto obj){
        return repository.save(obj);
    }
}
