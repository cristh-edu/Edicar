package com.edicar.carros.web.rest;

import com.edicar.carros.domain.Modelo;
import com.edicar.carros.service.ModeloService;
import com.edicar.carros.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.edicar.carros.domain.Modelo}.
 */
@RestController
@RequestMapping("/api")
public class ModeloResource {

    private final Logger log = LoggerFactory.getLogger(ModeloResource.class);

    private static final String ENTITY_NAME = "modelo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ModeloService modeloService;

    public ModeloResource(ModeloService modeloService) {
        this.modeloService = modeloService;
    }

    /**
     * {@code POST  /modelos} : Create a new modelo.
     *
     * @param modelo the modelo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new modelo, or with status {@code 400 (Bad Request)} if the modelo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/modelos")
    public ResponseEntity<Modelo> createModelo(@Valid @RequestBody Modelo modelo) throws URISyntaxException {
        log.debug("REST request to save Modelo : {}", modelo);
        if (modelo.getId() != null) {
            throw new BadRequestAlertException("A new modelo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Modelo result = modeloService.save(modelo);
        return ResponseEntity.created(new URI("/api/modelos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /modelos} : Updates an existing modelo.
     *
     * @param modelo the modelo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated modelo,
     * or with status {@code 400 (Bad Request)} if the modelo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the modelo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/modelos")
    public ResponseEntity<Modelo> updateModelo(@Valid @RequestBody Modelo modelo) throws URISyntaxException {
        log.debug("REST request to update Modelo : {}", modelo);
        if (modelo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Modelo result = modeloService.save(modelo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, modelo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /modelos} : get all the modelos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of modelos in body.
     */
    @GetMapping("/modelos")
    public ResponseEntity<List<Modelo>> getAllModelos(Pageable pageable) {
        log.debug("REST request to get a page of Modelos");
        Page<Modelo> page = modeloService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /modelos/:id} : get the "id" modelo.
     *
     * @param id the id of the modelo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the modelo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/modelos/{id}")
    public ResponseEntity<Modelo> getModelo(@PathVariable Long id) {
        log.debug("REST request to get Modelo : {}", id);
        Optional<Modelo> modelo = modeloService.findOne(id);
        return ResponseUtil.wrapOrNotFound(modelo);
    }

    /**
     * {@code DELETE  /modelos/:id} : delete the "id" modelo.
     *
     * @param id the id of the modelo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/modelos/{id}")
    public ResponseEntity<Void> deleteModelo(@PathVariable Long id) {
        log.debug("REST request to delete Modelo : {}", id);

        modeloService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
