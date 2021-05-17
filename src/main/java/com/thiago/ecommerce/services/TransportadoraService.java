package com.thiago.ecommerce.services;
import com.thiago.ecommerce.entities.Transportadora;
import com.thiago.ecommerce.repositories.TransportadoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TransportadoraService {
    @Autowired
    private TransportadoraRepository repository;

    public List<Transportadora> findAll(){
        return repository.findAll();
    }

    public Transportadora findById(Long id){
        Optional<Transportadora> obj = repository.findById(id);
        return obj.get();
    }

    public Transportadora insert(Transportadora obj){
        return repository.save(obj);
    }
}
