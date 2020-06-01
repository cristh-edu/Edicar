package com.edicar.carros.service.impl;

import com.edicar.carros.service.VeiculoService;
import com.edicar.carros.domain.Veiculo;
import com.edicar.carros.repository.VeiculoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Veiculo}.
 */
@Service
@Transactional
public class VeiculoServiceImpl implements VeiculoService {

    private final Logger log = LoggerFactory.getLogger(VeiculoServiceImpl.class);

    private final VeiculoRepository veiculoRepository;

    public VeiculoServiceImpl(VeiculoRepository veiculoRepository) {
        this.veiculoRepository = veiculoRepository;
    }

    /**
     * Save a veiculo.
     *
     * @param veiculo the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Veiculo save(Veiculo veiculo) {
        log.debug("Request to save Veiculo : {}", veiculo);
        return veiculoRepository.save(veiculo);
    }

    /**
     * Get all the veiculos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Veiculo> findAll(Pageable pageable) {
        log.debug("Request to get all Veiculos");
        return veiculoRepository.findAll(pageable);
    }


    /**
     * Get one veiculo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Veiculo> findOne(Long id) {
        log.debug("Request to get Veiculo : {}", id);
        return veiculoRepository.findById(id);
    }

    /**
     * Delete the veiculo by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Veiculo : {}", id);

        veiculoRepository.deleteById(id);
    }
}
