package com.thiago.ecommerce.services;

import com.thiago.ecommerce.entities.Endereco;
import com.thiago.ecommerce.entities.Entrega;
import com.thiago.ecommerce.entities.Transportadora;
import com.thiago.ecommerce.repositories.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntregaService {
    @Autowired
    private EntregaRepository repository;
    @Autowired
    private EnderecoService enderecoService;
    @Autowired
    private TransportadoraService transportadoraService;

    public List<Entrega> findAll(){
        return repository.findAll();
    }

    public Entrega findById(Long id){
        Optional<Entrega> obj = repository.findById(id);
        return obj.get();
    }

    public Entrega insert(Long idEndereco, Long idTransportadora){
        Optional<Endereco> endereco = Optional.ofNullable(enderecoService.findById(idEndereco));
        Optional<Transportadora> transportadora = Optional.ofNullable(transportadoraService.findById(idTransportadora));

        if(endereco.isPresent() && transportadora.isPresent()){
            Entrega entrega = new Entrega(null, endereco.get(), transportadora.get());
            return repository.save(entrega);
        }

        return null;
    }
}
