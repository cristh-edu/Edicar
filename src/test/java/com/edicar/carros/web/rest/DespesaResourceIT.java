package com.edicar.carros.web.rest;

import com.edicar.carros.EdicarApp;
import com.edicar.carros.domain.Despesa;
import com.edicar.carros.repository.DespesaRepository;
import com.edicar.carros.service.DespesaService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link DespesaResource} REST controller.
 */
@SpringBootTest(classes = EdicarApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DespesaResourceIT {

    private static final LocalDate DEFAULT_DT_DESPESA = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DT_DESPESA = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_NM_DESPESA = "AAAAAAAAAA";
    private static final String UPDATED_NM_DESPESA = "BBBBBBBBBB";

    private static final Long DEFAULT_VALOR = 0L;
    private static final Long UPDATED_VALOR = 1L;

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private DespesaService despesaService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDespesaMockMvc;

    private Despesa despesa;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Despesa createEntity(EntityManager em) {
        Despesa despesa = new Despesa()
            .dtDespesa(DEFAULT_DT_DESPESA)
            .nmDespesa(DEFAULT_NM_DESPESA)
            .valor(DEFAULT_VALOR);
        return despesa;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Despesa createUpdatedEntity(EntityManager em) {
        Despesa despesa = new Despesa()
            .dtDespesa(UPDATED_DT_DESPESA)
            .nmDespesa(UPDATED_NM_DESPESA)
            .valor(UPDATED_VALOR);
        return despesa;
    }

    @BeforeEach
    public void initTest() {
        despesa = createEntity(em);
    }

    @Test
    @Transactional
    public void createDespesa() throws Exception {
        int databaseSizeBeforeCreate = despesaRepository.findAll().size();
        // Create the Despesa
        restDespesaMockMvc.perform(post("/api/despesas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(despesa)))
            .andExpect(status().isCreated());

        // Validate the Despesa in the database
        List<Despesa> despesaList = despesaRepository.findAll();
        assertThat(despesaList).hasSize(databaseSizeBeforeCreate + 1);
        Despesa testDespesa = despesaList.get(despesaList.size() - 1);
        assertThat(testDespesa.getDtDespesa()).isEqualTo(DEFAULT_DT_DESPESA);
        assertThat(testDespesa.getNmDespesa()).isEqualTo(DEFAULT_NM_DESPESA);
        assertThat(testDespesa.getValor()).isEqualTo(DEFAULT_VALOR);
    }

    @Test
    @Transactional
    public void createDespesaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = despesaRepository.findAll().size();

        // Create the Despesa with an existing ID
        despesa.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDespesaMockMvc.perform(post("/api/despesas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(despesa)))
            .andExpect(status().isBadRequest());

        // Validate the Despesa in the database
        List<Despesa> despesaList = despesaRepository.findAll();
        assertThat(despesaList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkDtDespesaIsRequired() throws Exception {
        int databaseSizeBeforeTest = despesaRepository.findAll().size();
        // set the field null
        despesa.setDtDespesa(null);

        // Create the Despesa, which fails.


        restDespesaMockMvc.perform(post("/api/despesas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(despesa)))
            .andExpect(status().isBadRequest());

        List<Despesa> despesaList = despesaRepository.findAll();
        assertThat(despesaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkNmDespesaIsRequired() throws Exception {
        int databaseSizeBeforeTest = despesaRepository.findAll().size();
        // set the field null
        despesa.setNmDespesa(null);

        // Create the Despesa, which fails.


        restDespesaMockMvc.perform(post("/api/despesas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(despesa)))
            .andExpect(status().isBadRequest());

        List<Despesa> despesaList = despesaRepository.findAll();
        assertThat(despesaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkValorIsRequired() throws Exception {
        int databaseSizeBeforeTest = despesaRepository.findAll().size();
        // set the field null
        despesa.setValor(null);

        // Create the Despesa, which fails.


        restDespesaMockMvc.perform(post("/api/despesas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(despesa)))
            .andExpect(status().isBadRequest());

        List<Despesa> despesaList = despesaRepository.findAll();
        assertThat(despesaList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllDespesas() throws Exception {
        // Initialize the database
        despesaRepository.saveAndFlush(despesa);

        // Get all the despesaList
        restDespesaMockMvc.perform(get("/api/despesas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(despesa.getId().intValue())))
            .andExpect(jsonPath("$.[*].dtDespesa").value(hasItem(DEFAULT_DT_DESPESA.toString())))
            .andExpect(jsonPath("$.[*].nmDespesa").value(hasItem(DEFAULT_NM_DESPESA)))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.intValue())));
    }
    
    @Test
    @Transactional
    public void getDespesa() throws Exception {
        // Initialize the database
        despesaRepository.saveAndFlush(despesa);

        // Get the despesa
        restDespesaMockMvc.perform(get("/api/despesas/{id}", despesa.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(despesa.getId().intValue()))
            .andExpect(jsonPath("$.dtDespesa").value(DEFAULT_DT_DESPESA.toString()))
            .andExpect(jsonPath("$.nmDespesa").value(DEFAULT_NM_DESPESA))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingDespesa() throws Exception {
        // Get the despesa
        restDespesaMockMvc.perform(get("/api/despesas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDespesa() throws Exception {
        // Initialize the database
        despesaService.save(despesa);

        int databaseSizeBeforeUpdate = despesaRepository.findAll().size();

        // Update the despesa
        Despesa updatedDespesa = despesaRepository.findById(despesa.getId()).get();
        // Disconnect from session so that the updates on updatedDespesa are not directly saved in db
        em.detach(updatedDespesa);
        updatedDespesa
            .dtDespesa(UPDATED_DT_DESPESA)
            .nmDespesa(UPDATED_NM_DESPESA)
            .valor(UPDATED_VALOR);

        restDespesaMockMvc.perform(put("/api/despesas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedDespesa)))
            .andExpect(status().isOk());

        // Validate the Despesa in the database
        List<Despesa> despesaList = despesaRepository.findAll();
        assertThat(despesaList).hasSize(databaseSizeBeforeUpdate);
        Despesa testDespesa = despesaList.get(despesaList.size() - 1);
        assertThat(testDespesa.getDtDespesa()).isEqualTo(UPDATED_DT_DESPESA);
        assertThat(testDespesa.getNmDespesa()).isEqualTo(UPDATED_NM_DESPESA);
        assertThat(testDespesa.getValor()).isEqualTo(UPDATED_VALOR);
    }

    @Test
    @Transactional
    public void updateNonExistingDespesa() throws Exception {
        int databaseSizeBeforeUpdate = despesaRepository.findAll().size();

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDespesaMockMvc.perform(put("/api/despesas")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(despesa)))
            .andExpect(status().isBadRequest());

        // Validate the Despesa in the database
        List<Despesa> despesaList = despesaRepository.findAll();
        assertThat(despesaList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDespesa() throws Exception {
        // Initialize the database
        despesaService.save(despesa);

        int databaseSizeBeforeDelete = despesaRepository.findAll().size();

        // Delete the despesa
        restDespesaMockMvc.perform(delete("/api/despesas/{id}", despesa.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Despesa> despesaList = despesaRepository.findAll();
        assertThat(despesaList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
