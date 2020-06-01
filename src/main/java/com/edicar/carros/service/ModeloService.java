package com.edicar.carros.service;

import com.edicar.carros.domain.Modelo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Modelo}.
 */
public interface ModeloService {

    /**
     * Save a modelo.
     *
     * @param modelo the entity to save.
     * @return the persisted entity.
     */
    Modelo save(Modelo modelo);

    /**
     * Get all the modelos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Modelo> findAll(Pageable pageable);


    /**
     * Get the "id" modelo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Modelo> findOne(Long id);

    /**
     * Delete the "id" modelo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
