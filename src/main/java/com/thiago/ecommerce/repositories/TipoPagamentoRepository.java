package com.thiago.ecommerce.repositories;

import com.thiago.ecommerce.entities.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long>{
}
