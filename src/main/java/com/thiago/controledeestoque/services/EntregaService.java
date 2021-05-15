package com.thiago.controledeestoque.services;

import com.thiago.controledeestoque.entities.Entrega;
import com.thiago.controledeestoque.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository repository;

    public List<Entrega> findAll(){
        return repository.findAll();
    }

    public Entrega findById(Long id){
        Optional<Entrega> obj = repository.findById(id);
        return obj.get();
    }

    public Entrega insert(Entrega obj){
        return repository.save(obj);
    }
}
