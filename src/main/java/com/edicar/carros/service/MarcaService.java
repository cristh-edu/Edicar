package com.edicar.carros.service;

import com.edicar.carros.domain.Marca;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Marca}.
 */
public interface MarcaService {

    /**
     * Save a marca.
     *
     * @param marca the entity to save.
     * @return the persisted entity.
     */
    Marca save(Marca marca);

    /**
     * Get all the marcas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Marca> findAll(Pageable pageable);


    /**
     * Get the "id" marca.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Marca> findOne(Long id);

    /**
     * Delete the "id" marca.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
