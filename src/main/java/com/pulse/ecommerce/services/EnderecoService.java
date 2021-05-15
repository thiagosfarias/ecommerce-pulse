package com.pulse.ecommerce.services;

import com.pulse.ecommerce.repositories.EnderecoRepository;
import com.pulse.ecommerce.entities.Endereco;
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
        Optional<Endereco> obj = repository.findById(id);
        return obj.get();
    }

    public Endereco insert(Endereco obj){
        return repository.save(obj);
    }
}
