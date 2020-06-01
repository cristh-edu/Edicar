package com.edicar.carros.service.impl;

import com.edicar.carros.service.ModeloService;
import com.edicar.carros.domain.Modelo;
import com.edicar.carros.repository.ModeloRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Modelo}.
 */
@Service
@Transactional
public class ModeloServiceImpl implements ModeloService {

    private final Logger log = LoggerFactory.getLogger(ModeloServiceImpl.class);

    private final ModeloRepository modeloRepository;

    public ModeloServiceImpl(ModeloRepository modeloRepository) {
        this.modeloRepository = modeloRepository;
    }

    /**
     * Save a modelo.
     *
     * @param modelo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Modelo save(Modelo modelo) {
        log.debug("Request to save Modelo : {}", modelo);
        return modeloRepository.save(modelo);
    }

    /**
     * Get all the modelos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Modelo> findAll(Pageable pageable) {
        log.debug("Request to get all Modelos");
        return modeloRepository.findAll(pageable);
    }


    /**
     * Get one modelo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Modelo> findOne(Long id) {
        log.debug("Request to get Modelo : {}", id);
        return modeloRepository.findById(id);
    }

    /**
     * Delete the modelo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Modelo : {}", id);

        modeloRepository.deleteById(id);
    }
}
