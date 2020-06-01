package com.edicar.carros.service;

import com.edicar.carros.domain.Venda;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Venda}.
 */
public interface VendaService {

    /**
     * Save a venda.
     *
     * @param venda the entity to save.
     * @return the persisted entity.
     */
    Venda save(Venda venda);

    /**
     * Get all the vendas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Venda> findAll(Pageable pageable);


    /**
     * Get the "id" venda.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Venda> findOne(Long id);

    /**
     * Delete the "id" venda.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
