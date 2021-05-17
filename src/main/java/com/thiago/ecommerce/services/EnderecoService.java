package com.thiago.ecommerce.services;

import com.thiago.ecommerce.entities.Cliente;
import com.thiago.ecommerce.repositories.EnderecoRepository;
import com.thiago.ecommerce.entities.Endereco;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;
    @Autowired
    private ClienteService clienteService;

    public List<Endereco> findAll(){
        return Optional.ofNullable(repository.findAll()).get();
    }

    public Endereco findById(Long id){
        return Optional.ofNullable( repository.findById(id) ).get().orElseThrow();
    }

    public Endereco insert(Endereco obj, Long id) {
        Optional<Cliente> cliente = Optional.ofNullable(clienteService.findById(id));

        if (cliente.isPresent() && obj.getTitular() == null) {
            clienteService.newEndereco(id, obj);
            return repository.save(obj);
        }
        return null;
    }
}
