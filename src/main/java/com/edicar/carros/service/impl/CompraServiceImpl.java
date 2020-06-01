package com.edicar.carros.service.impl;

import com.edicar.carros.service.CompraService;
import com.edicar.carros.domain.Compra;
import com.edicar.carros.repository.CompraRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Compra}.
 */
@Service
@Transactional
public class CompraServiceImpl implements CompraService {

    private final Logger log = LoggerFactory.getLogger(CompraServiceImpl.class);

    private final CompraRepository compraRepository;

    public CompraServiceImpl(CompraRepository compraRepository) {
        this.compraRepository = compraRepository;
    }

    /**
     * Save a compra.
     *
     * @param compra the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Compra save(Compra compra) {
        log.debug("Request to save Compra : {}", compra);
        return compraRepository.save(compra);
    }

    /**
     * Get all the compras.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Compra> findAll(Pageable pageable) {
        log.debug("Request to get all Compras");
        return compraRepository.findAll(pageable);
    }


    /**
     * Get one compra by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Compra> findOne(Long id) {
        log.debug("Request to get Compra : {}", id);
        return compraRepository.findById(id);
    }

    /**
     * Delete the compra by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Compra : {}", id);

        compraRepository.deleteById(id);
    }
}
