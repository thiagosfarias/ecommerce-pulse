package com.pulse.ecommerce.repositories;

import com.pulse.ecommerce.entities.Transportadora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {
}
