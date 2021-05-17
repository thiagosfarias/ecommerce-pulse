package com.thiago.ecommerce.repositories;

import com.thiago.ecommerce.entities.Cupom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {
}
