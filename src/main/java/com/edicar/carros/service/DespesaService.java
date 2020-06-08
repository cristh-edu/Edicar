package com.edicar.carros.service;

import com.edicar.carros.domain.Despesa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Despesa}.
 */
public interface DespesaService {

    /**
     * Save a despesa.
     *
     * @param despesa the entity to save.
     * @return the persisted entity.
     */
    Despesa save(Despesa despesa);

    /**
     * Get all the despesas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Despesa> findAll(Pageable pageable);


    /**
     * Get the "id" despesa.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Despesa> findOne(Long id);

    /**
     * Delete the "id" despesa.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
