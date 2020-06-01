package com.edicar.carros.service;

import com.edicar.carros.domain.Compra;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Compra}.
 */
public interface CompraService {

    /**
     * Save a compra.
     *
     * @param compra the entity to save.
     * @return the persisted entity.
     */
    Compra save(Compra compra);

    /**
     * Get all the compras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Compra> findAll(Pageable pageable);


    /**
     * Get the "id" compra.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Compra> findOne(Long id);

    /**
     * Delete the "id" compra.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
