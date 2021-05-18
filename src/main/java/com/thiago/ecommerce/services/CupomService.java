package com.thiago.ecommerce.services;

import com.thiago.ecommerce.entities.Cupom;
import com.thiago.ecommerce.repositories.CupomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Service
public class CupomService {
    @Autowired
    private CupomRepository repository;

    public List<Cupom> findAll(){
        return Optional.ofNullable(repository.findAll()).get();
    }

    public Cupom findById(Long id){
        return Optional.ofNullable(repository.findById(id)).get().orElseThrow();
    }

    public Cupom novoCupom(Cupom obj){
        return Optional.ofNullable(repository.save(obj)).get();
    }

}
