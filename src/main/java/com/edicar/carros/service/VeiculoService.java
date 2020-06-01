package com.edicar.carros.service;

import com.edicar.carros.domain.Veiculo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Veiculo}.
 */
public interface VeiculoService {

    /**
     * Save a veiculo.
     *
     * @param veiculo the entity to save.
     * @return the persisted entity.
     */
    Veiculo save(Veiculo veiculo);

    /**
     * Get all the veiculos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Veiculo> findAll(Pageable pageable);


    /**
     * Get the "id" veiculo.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Veiculo> findOne(Long id);

    /**
     * Delete the "id" veiculo.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
