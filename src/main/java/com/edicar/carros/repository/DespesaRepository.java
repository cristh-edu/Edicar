package com.edicar.carros.repository;

import com.edicar.carros.domain.Despesa;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Despesa entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
