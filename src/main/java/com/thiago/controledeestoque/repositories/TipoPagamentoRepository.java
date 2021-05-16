package com.thiago.controledeestoque.repositories;

import com.thiago.controledeestoque.entities.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long>{
}
