package com.edicar.carros.service.impl;

import com.edicar.carros.service.DespesaService;
import com.edicar.carros.domain.Despesa;
import com.edicar.carros.repository.DespesaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Despesa}.
 */
@Service
@Transactional
public class DespesaServiceImpl implements DespesaService {

    private final Logger log = LoggerFactory.getLogger(DespesaServiceImpl.class);

    private final DespesaRepository despesaRepository;

    public DespesaServiceImpl(DespesaRepository despesaRepository) {
        this.despesaRepository = despesaRepository;
    }

    /**
     * Save a despesa.
     *
     * @param despesa the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Despesa save(Despesa despesa) {
        log.debug("Request to save Despesa : {}", despesa);
        return despesaRepository.save(despesa);
    }

    /**
     * Get all the despesas.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Despesa> findAll(Pageable pageable) {
        log.debug("Request to get all Despesas");
        return despesaRepository.findAll(pageable);
    }


    /**
     * Get one despesa by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Despesa> findOne(Long id) {
        log.debug("Request to get Despesa : {}", id);
        return despesaRepository.findById(id);
    }

    /**
     * Delete the despesa by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Despesa : {}", id);

        despesaRepository.deleteById(id);
    }
}
