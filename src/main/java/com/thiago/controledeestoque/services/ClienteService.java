package com.thiago.controledeestoque.services;

import com.thiago.controledeestoque.entities.Cliente;
import com.thiago.controledeestoque.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente findById(Long id){
        return Optional.ofNullable( repository.findById(id) ).get().orElseThrow();
    }

    public Cliente insert(Cliente obj){
        return repository.save(obj);
    }

    public Cliente update(Cliente obj) { return repository.save(obj); }
}
