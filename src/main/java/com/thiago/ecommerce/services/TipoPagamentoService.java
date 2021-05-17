package com.thiago.ecommerce.services;

import com.thiago.ecommerce.entities.TipoPagamento;
import com.thiago.ecommerce.repositories.TipoPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoPagamentoService {
    @Autowired
    private TipoPagamentoRepository repository;

    public TipoPagamento insert(TipoPagamento obj){
        return Optional.ofNullable(repository.save(obj)).get();
    }

}
