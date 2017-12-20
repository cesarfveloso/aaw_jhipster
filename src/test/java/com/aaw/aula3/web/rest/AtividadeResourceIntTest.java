package com.aaw.aula3.web.rest;

import com.aaw.aula3.AawAula3App;

import com.aaw.aula3.domain.Atividade;
import com.aaw.aula3.repository.AtividadeRepository;
import com.aaw.aula3.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.aaw.aula3.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the AtividadeResource REST controller.
 *
 * @see AtividadeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AawAula3App.class)
public class AtividadeResourceIntTest {

    private static final Float DEFAULT_VALOR = 1F;
    private static final Float UPDATED_VALOR = 2F;

    private static final String DEFAULT_TIPO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO = "BBBBBBBBBB";

    private static final String DEFAULT_DATA_FINAL = "AAAAAAAAAA";
    private static final String UPDATED_DATA_FINAL = "BBBBBBBBBB";

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAtividadeMockMvc;

    private Atividade atividade;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AtividadeResource atividadeResource = new AtividadeResource(atividadeRepository);
        this.restAtividadeMockMvc = MockMvcBuilders.standaloneSetup(atividadeResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Atividade createEntity(EntityManager em) {
        Atividade atividade = new Atividade()
            .valor(DEFAULT_VALOR)
            .tipo(DEFAULT_TIPO)
            .dataFinal(DEFAULT_DATA_FINAL);
        return atividade;
    }

    @Before
    public void initTest() {
        atividade = createEntity(em);
    }

    @Test
    @Transactional
    public void createAtividade() throws Exception {
        int databaseSizeBeforeCreate = atividadeRepository.findAll().size();

        // Create the Atividade
        restAtividadeMockMvc.perform(post("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividade)))
            .andExpect(status().isCreated());

        // Validate the Atividade in the database
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeCreate + 1);
        Atividade testAtividade = atividadeList.get(atividadeList.size() - 1);
        assertThat(testAtividade.getValor()).isEqualTo(DEFAULT_VALOR);
        assertThat(testAtividade.getTipo()).isEqualTo(DEFAULT_TIPO);
        assertThat(testAtividade.getDataFinal()).isEqualTo(DEFAULT_DATA_FINAL);
    }

    @Test
    @Transactional
    public void createAtividadeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = atividadeRepository.findAll().size();

        // Create the Atividade with an existing ID
        atividade.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAtividadeMockMvc.perform(post("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividade)))
            .andExpect(status().isBadRequest());

        // Validate the Atividade in the database
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAtividades() throws Exception {
        // Initialize the database
        atividadeRepository.saveAndFlush(atividade);

        // Get all the atividadeList
        restAtividadeMockMvc.perform(get("/api/atividades?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(atividade.getId().intValue())))
            .andExpect(jsonPath("$.[*].valor").value(hasItem(DEFAULT_VALOR.doubleValue())))
            .andExpect(jsonPath("$.[*].tipo").value(hasItem(DEFAULT_TIPO.toString())))
            .andExpect(jsonPath("$.[*].dataFinal").value(hasItem(DEFAULT_DATA_FINAL.toString())));
    }

    @Test
    @Transactional
    public void getAtividade() throws Exception {
        // Initialize the database
        atividadeRepository.saveAndFlush(atividade);

        // Get the atividade
        restAtividadeMockMvc.perform(get("/api/atividades/{id}", atividade.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(atividade.getId().intValue()))
            .andExpect(jsonPath("$.valor").value(DEFAULT_VALOR.doubleValue()))
            .andExpect(jsonPath("$.tipo").value(DEFAULT_TIPO.toString()))
            .andExpect(jsonPath("$.dataFinal").value(DEFAULT_DATA_FINAL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAtividade() throws Exception {
        // Get the atividade
        restAtividadeMockMvc.perform(get("/api/atividades/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAtividade() throws Exception {
        // Initialize the database
        atividadeRepository.saveAndFlush(atividade);
        int databaseSizeBeforeUpdate = atividadeRepository.findAll().size();

        // Update the atividade
        Atividade updatedAtividade = atividadeRepository.findOne(atividade.getId());
        // Disconnect from session so that the updates on updatedAtividade are not directly saved in db
        em.detach(updatedAtividade);
        updatedAtividade
            .valor(UPDATED_VALOR)
            .tipo(UPDATED_TIPO)
            .dataFinal(UPDATED_DATA_FINAL);

        restAtividadeMockMvc.perform(put("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAtividade)))
            .andExpect(status().isOk());

        // Validate the Atividade in the database
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeUpdate);
        Atividade testAtividade = atividadeList.get(atividadeList.size() - 1);
        assertThat(testAtividade.getValor()).isEqualTo(UPDATED_VALOR);
        assertThat(testAtividade.getTipo()).isEqualTo(UPDATED_TIPO);
        assertThat(testAtividade.getDataFinal()).isEqualTo(UPDATED_DATA_FINAL);
    }

    @Test
    @Transactional
    public void updateNonExistingAtividade() throws Exception {
        int databaseSizeBeforeUpdate = atividadeRepository.findAll().size();

        // Create the Atividade

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAtividadeMockMvc.perform(put("/api/atividades")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(atividade)))
            .andExpect(status().isCreated());

        // Validate the Atividade in the database
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAtividade() throws Exception {
        // Initialize the database
        atividadeRepository.saveAndFlush(atividade);
        int databaseSizeBeforeDelete = atividadeRepository.findAll().size();

        // Get the atividade
        restAtividadeMockMvc.perform(delete("/api/atividades/{id}", atividade.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Atividade> atividadeList = atividadeRepository.findAll();
        assertThat(atividadeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Atividade.class);
        Atividade atividade1 = new Atividade();
        atividade1.setId(1L);
        Atividade atividade2 = new Atividade();
        atividade2.setId(atividade1.getId());
        assertThat(atividade1).isEqualTo(atividade2);
        atividade2.setId(2L);
        assertThat(atividade1).isNotEqualTo(atividade2);
        atividade1.setId(null);
        assertThat(atividade1).isNotEqualTo(atividade2);
    }
}
