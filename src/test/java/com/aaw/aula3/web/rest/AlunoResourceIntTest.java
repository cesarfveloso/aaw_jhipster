package com.aaw.aula3.web.rest;

import com.aaw.aula3.AawAula3App;

import com.aaw.aula3.domain.Aluno;
import com.aaw.aula3.repository.AlunoRepository;
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
 * Test class for the AlunoResource REST controller.
 *
 * @see AlunoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AawAula3App.class)
public class AlunoResourceIntTest {

    private static final String DEFAULT_NOME = "AAAAAAAAAA";
    private static final String UPDATED_NOME = "BBBBBBBBBB";

    private static final String DEFAULT_TIPO_INGRESSO = "AAAAAAAAAA";
    private static final String UPDATED_TIPO_INGRESSO = "BBBBBBBBBB";

    private static final Boolean DEFAULT_BOLSISTA = false;
    private static final Boolean UPDATED_BOLSISTA = true;

    private static final String DEFAULT_DATA_INGRESSO = "AAAAAAAAAA";
    private static final String UPDATED_DATA_INGRESSO = "BBBBBBBBBB";

    private static final String DEFAULT_OBSERVACOES = "AAAAAAAAAA";
    private static final String UPDATED_OBSERVACOES = "BBBBBBBBBB";

    @Autowired
    private AlunoRepository alunoRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAlunoMockMvc;

    private Aluno aluno;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AlunoResource alunoResource = new AlunoResource(alunoRepository);
        this.restAlunoMockMvc = MockMvcBuilders.standaloneSetup(alunoResource)
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
    public static Aluno createEntity(EntityManager em) {
        Aluno aluno = new Aluno()
            .nome(DEFAULT_NOME)
            .tipoIngresso(DEFAULT_TIPO_INGRESSO)
            .bolsista(DEFAULT_BOLSISTA)
            .dataIngresso(DEFAULT_DATA_INGRESSO)
            .observacoes(DEFAULT_OBSERVACOES);
        return aluno;
    }

    @Before
    public void initTest() {
        aluno = createEntity(em);
    }

    @Test
    @Transactional
    public void createAluno() throws Exception {
        int databaseSizeBeforeCreate = alunoRepository.findAll().size();

        // Create the Aluno
        restAlunoMockMvc.perform(post("/api/alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluno)))
            .andExpect(status().isCreated());

        // Validate the Aluno in the database
        List<Aluno> alunoList = alunoRepository.findAll();
        assertThat(alunoList).hasSize(databaseSizeBeforeCreate + 1);
        Aluno testAluno = alunoList.get(alunoList.size() - 1);
        assertThat(testAluno.getNome()).isEqualTo(DEFAULT_NOME);
        assertThat(testAluno.getTipoIngresso()).isEqualTo(DEFAULT_TIPO_INGRESSO);
        assertThat(testAluno.isBolsista()).isEqualTo(DEFAULT_BOLSISTA);
        assertThat(testAluno.getDataIngresso()).isEqualTo(DEFAULT_DATA_INGRESSO);
        assertThat(testAluno.getObservacoes()).isEqualTo(DEFAULT_OBSERVACOES);
    }

    @Test
    @Transactional
    public void createAlunoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = alunoRepository.findAll().size();

        // Create the Aluno with an existing ID
        aluno.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAlunoMockMvc.perform(post("/api/alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluno)))
            .andExpect(status().isBadRequest());

        // Validate the Aluno in the database
        List<Aluno> alunoList = alunoRepository.findAll();
        assertThat(alunoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllAlunos() throws Exception {
        // Initialize the database
        alunoRepository.saveAndFlush(aluno);

        // Get all the alunoList
        restAlunoMockMvc.perform(get("/api/alunos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(aluno.getId().intValue())))
            .andExpect(jsonPath("$.[*].nome").value(hasItem(DEFAULT_NOME.toString())))
            .andExpect(jsonPath("$.[*].tipoIngresso").value(hasItem(DEFAULT_TIPO_INGRESSO.toString())))
            .andExpect(jsonPath("$.[*].bolsista").value(hasItem(DEFAULT_BOLSISTA.booleanValue())))
            .andExpect(jsonPath("$.[*].dataIngresso").value(hasItem(DEFAULT_DATA_INGRESSO.toString())))
            .andExpect(jsonPath("$.[*].observacoes").value(hasItem(DEFAULT_OBSERVACOES.toString())));
    }

    @Test
    @Transactional
    public void getAluno() throws Exception {
        // Initialize the database
        alunoRepository.saveAndFlush(aluno);

        // Get the aluno
        restAlunoMockMvc.perform(get("/api/alunos/{id}", aluno.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(aluno.getId().intValue()))
            .andExpect(jsonPath("$.nome").value(DEFAULT_NOME.toString()))
            .andExpect(jsonPath("$.tipoIngresso").value(DEFAULT_TIPO_INGRESSO.toString()))
            .andExpect(jsonPath("$.bolsista").value(DEFAULT_BOLSISTA.booleanValue()))
            .andExpect(jsonPath("$.dataIngresso").value(DEFAULT_DATA_INGRESSO.toString()))
            .andExpect(jsonPath("$.observacoes").value(DEFAULT_OBSERVACOES.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAluno() throws Exception {
        // Get the aluno
        restAlunoMockMvc.perform(get("/api/alunos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAluno() throws Exception {
        // Initialize the database
        alunoRepository.saveAndFlush(aluno);
        int databaseSizeBeforeUpdate = alunoRepository.findAll().size();

        // Update the aluno
        Aluno updatedAluno = alunoRepository.findOne(aluno.getId());
        // Disconnect from session so that the updates on updatedAluno are not directly saved in db
        em.detach(updatedAluno);
        updatedAluno
            .nome(UPDATED_NOME)
            .tipoIngresso(UPDATED_TIPO_INGRESSO)
            .bolsista(UPDATED_BOLSISTA)
            .dataIngresso(UPDATED_DATA_INGRESSO)
            .observacoes(UPDATED_OBSERVACOES);

        restAlunoMockMvc.perform(put("/api/alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedAluno)))
            .andExpect(status().isOk());

        // Validate the Aluno in the database
        List<Aluno> alunoList = alunoRepository.findAll();
        assertThat(alunoList).hasSize(databaseSizeBeforeUpdate);
        Aluno testAluno = alunoList.get(alunoList.size() - 1);
        assertThat(testAluno.getNome()).isEqualTo(UPDATED_NOME);
        assertThat(testAluno.getTipoIngresso()).isEqualTo(UPDATED_TIPO_INGRESSO);
        assertThat(testAluno.isBolsista()).isEqualTo(UPDATED_BOLSISTA);
        assertThat(testAluno.getDataIngresso()).isEqualTo(UPDATED_DATA_INGRESSO);
        assertThat(testAluno.getObservacoes()).isEqualTo(UPDATED_OBSERVACOES);
    }

    @Test
    @Transactional
    public void updateNonExistingAluno() throws Exception {
        int databaseSizeBeforeUpdate = alunoRepository.findAll().size();

        // Create the Aluno

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAlunoMockMvc.perform(put("/api/alunos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(aluno)))
            .andExpect(status().isCreated());

        // Validate the Aluno in the database
        List<Aluno> alunoList = alunoRepository.findAll();
        assertThat(alunoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAluno() throws Exception {
        // Initialize the database
        alunoRepository.saveAndFlush(aluno);
        int databaseSizeBeforeDelete = alunoRepository.findAll().size();

        // Get the aluno
        restAlunoMockMvc.perform(delete("/api/alunos/{id}", aluno.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Aluno> alunoList = alunoRepository.findAll();
        assertThat(alunoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Aluno.class);
        Aluno aluno1 = new Aluno();
        aluno1.setId(1L);
        Aluno aluno2 = new Aluno();
        aluno2.setId(aluno1.getId());
        assertThat(aluno1).isEqualTo(aluno2);
        aluno2.setId(2L);
        assertThat(aluno1).isNotEqualTo(aluno2);
        aluno1.setId(null);
        assertThat(aluno1).isNotEqualTo(aluno2);
    }
}
