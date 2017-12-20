package com.aaw.aula3.web.rest;

import com.aaw.aula3.Aula3App;

import com.aaw.aula3.domain.Disciplina;
import com.aaw.aula3.repository.DisciplinaRepository;
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
 * Test class for the DisciplinaResource REST controller.
 *
 * @see DisciplinaResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Aula3App.class)
public class DisciplinaResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final Float DEFAULT_CARGA_HORARIA = 1F;
    private static final Float UPDATED_CARGA_HORARIA = 2F;

    private static final String DEFAULT_SIGLA = "AAAAAAAAAA";
    private static final String UPDATED_SIGLA = "BBBBBBBBBB";

    private static final String DEFAULT_EMENTA = "AAAAAAAAAA";
    private static final String UPDATED_EMENTA = "BBBBBBBBBB";

    private static final Integer DEFAULT_CREDITOS = 1;
    private static final Integer UPDATED_CREDITOS = 2;

    @Autowired
    private DisciplinaRepository disciplinaRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restDisciplinaMockMvc;

    private Disciplina disciplina;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DisciplinaResource disciplinaResource = new DisciplinaResource(disciplinaRepository);
        this.restDisciplinaMockMvc = MockMvcBuilders.standaloneSetup(disciplinaResource)
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
    public static Disciplina createEntity(EntityManager em) {
        Disciplina disciplina = new Disciplina()
            .nome(DEFAULT_NOME)
            .cargaHoraria(DEFAULT_CARGA_HORARIA)
            .sigla(DEFAULT_SIGLA)
            .ementa(DEFAULT_EMENTA)
            .creditos(DEFAULT_CREDITOS);
        return disciplina;
    }

    @Before
    public void initTest() {
        disciplina = createEntity(em);
    }

    @Test
    @Transactional
    public void createDisciplina() throws Exception {
        int databaseSizeBeforeCreate = disciplinaRepository.findAll().size();

        // Create the Disciplina
        restDisciplinaMockMvc.perform(post("/api/disciplinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disciplina)))
            .andExpect(status().isCreated());

        // Validate the Disciplina in the database
        List<Disciplina> disciplinaList = disciplinaRepository.findAll();
        assertThat(disciplinaList).hasSize(databaseSizeBeforeCreate + 1);
        Disciplina testDisciplina = disciplinaList.get(disciplinaList.size() - 1);
        assertThat(testDisciplina.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testDisciplina.getCargaHoraria()).isEqualTo(DEFAULT_CARGA_HORARIA);
        assertThat(testDisciplina.getSigla()).isEqualTo(DEFAULT_SIGLA);
        assertThat(testDisciplina.getEmenta()).isEqualTo(DEFAULT_EMENTA);
        assertThat(testDisciplina.getCreditos()).isEqualTo(DEFAULT_CREDITOS);
    }

    @Test
    @Transactional
    public void createDisciplinaWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = disciplinaRepository.findAll().size();

        // Create the Disciplina with an existing ID
        disciplina.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDisciplinaMockMvc.perform(post("/api/disciplinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disciplina)))
            .andExpect(status().isBadRequest());

        // Validate the Disciplina in the database
        List<Disciplina> disciplinaList = disciplinaRepository.findAll();
        assertThat(disciplinaList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDisciplinas() throws Exception {
        // Initialize the database
        disciplinaRepository.saveAndFlush(disciplina);

        // Get all the disciplinaList
        restDisciplinaMockMvc.perform(get("/api/disciplinas?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(disciplina.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].cargaHoraria").value(hasItem(DEFAULT_CARGA_HORARIA.doubleValue())))
            .andExpect(jsonPath("$.[*].sigla").value(hasItem(DEFAULT_SIGLA.toString())))
            .andExpect(jsonPath("$.[*].ementa").value(hasItem(DEFAULT_EMENTA.toString())))
            .andExpect(jsonPath("$.[*].creditos").value(hasItem(DEFAULT_CREDITOS)));
    }

    @Test
    @Transactional
    public void getDisciplina() throws Exception {
        // Initialize the database
        disciplinaRepository.saveAndFlush(disciplina);

        // Get the disciplina
        restDisciplinaMockMvc.perform(get("/api/disciplinas/{id}", disciplina.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(disciplina.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.cargaHoraria").value(DEFAULT_CARGA_HORARIA.doubleValue()))
            .andExpect(jsonPath("$.sigla").value(DEFAULT_SIGLA.toString()))
            .andExpect(jsonPath("$.ementa").value(DEFAULT_EMENTA.toString()))
            .andExpect(jsonPath("$.creditos").value(DEFAULT_CREDITOS));
    }

    @Test
    @Transactional
    public void getNonExistingDisciplina() throws Exception {
        // Get the disciplina
        restDisciplinaMockMvc.perform(get("/api/disciplinas/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDisciplina() throws Exception {
        // Initialize the database
        disciplinaRepository.saveAndFlush(disciplina);
        int databaseSizeBeforeUpdate = disciplinaRepository.findAll().size();

        // Update the disciplina
        Disciplina updatedDisciplina = disciplinaRepository.findOne(disciplina.getId());
        // Disconnect from session so that the updates on updatedDisciplina are not directly saved in db
        em.detach(updatedDisciplina);
        updatedDisciplina
            .nome(UPDATED_NOME)
            .cargaHoraria(UPDATED_CARGA_HORARIA)
            .sigla(UPDATED_SIGLA)
            .ementa(UPDATED_EMENTA)
            .creditos(UPDATED_CREDITOS);

        restDisciplinaMockMvc.perform(put("/api/disciplinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedDisciplina)))
            .andExpect(status().isOk());

        // Validate the Disciplina in the database
        List<Disciplina> disciplinaList = disciplinaRepository.findAll();
        assertThat(disciplinaList).hasSize(databaseSizeBeforeUpdate);
        Disciplina testDisciplina = disciplinaList.get(disciplinaList.size() - 1);
        assertThat(testDisciplina.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testDisciplina.getCargaHoraria()).isEqualTo(UPDATED_CARGA_HORARIA);
        assertThat(testDisciplina.getSigla()).isEqualTo(UPDATED_SIGLA);
        assertThat(testDisciplina.getEmenta()).isEqualTo(UPDATED_EMENTA);
        assertThat(testDisciplina.getCreditos()).isEqualTo(UPDATED_CREDITOS);
    }

    @Test
    @Transactional
    public void updateNonExistingDisciplina() throws Exception {
        int databaseSizeBeforeUpdate = disciplinaRepository.findAll().size();

        // Create the Disciplina

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restDisciplinaMockMvc.perform(put("/api/disciplinas")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(disciplina)))
            .andExpect(status().isCreated());

        // Validate the Disciplina in the database
        List<Disciplina> disciplinaList = disciplinaRepository.findAll();
        assertThat(disciplinaList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteDisciplina() throws Exception {
        // Initialize the database
        disciplinaRepository.saveAndFlush(disciplina);
        int databaseSizeBeforeDelete = disciplinaRepository.findAll().size();

        // Get the disciplina
        restDisciplinaMockMvc.perform(delete("/api/disciplinas/{id}", disciplina.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Disciplina> disciplinaList = disciplinaRepository.findAll();
        assertThat(disciplinaList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Disciplina.class);
        Disciplina disciplina1 = new Disciplina();
        disciplina1.setId(1L);
        Disciplina disciplina2 = new Disciplina();
        disciplina2.setId(disciplina1.getId());
        assertThat(disciplina1).isEqualTo(disciplina2);
        disciplina2.setId(2L);
        assertThat(disciplina1).isNotEqualTo(disciplina2);
        disciplina1.setId(null);
        assertThat(disciplina1).isNotEqualTo(disciplina2);
    }
}
