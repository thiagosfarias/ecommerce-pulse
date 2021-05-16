package com.thiago.controledeestoque.services;

import com.thiago.controledeestoque.repositories.EnderecoRepository;
import com.thiago.controledeestoque.entities.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public List<Endereco> findAll(){
        return repository.findAll();
    }

    public Endereco findById(Long id){
        return Optional.ofNullable( repository.findById(id) ).get().orElseThrow();
    }

    public Endereco insert(Endereco obj){
        return repository.save(obj);
    }
}
