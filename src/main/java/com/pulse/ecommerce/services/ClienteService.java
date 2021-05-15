package com.pulse.ecommerce.services;

import com.pulse.ecommerce.entities.Cliente;
import com.pulse.ecommerce.repositories.ClienteRepository;
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
        Optional<Cliente> obj = repository.findById(id);
        return obj.get();
    }

    public Cliente insert(Cliente obj){
        return repository.save(obj);
    }
}
