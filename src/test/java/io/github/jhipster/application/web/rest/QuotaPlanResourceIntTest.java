package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.QuotaPlan;
import io.github.jhipster.application.repository.QuotaPlanRepository;
import io.github.jhipster.application.service.dto.QuotaPlanDTO;
import io.github.jhipster.application.service.mapper.QuotaPlanMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the QuotaPlanResource REST controller.
 *
 * @see QuotaPlanResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class QuotaPlanResourceIntTest {

    private static final String DEFAULT_QUOTA_ID = "AAAAAAAAAA";
    private static final String UPDATED_QUOTA_ID = "BBBBBBBBBB";

    private static final String DEFAULT_APPT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_APPT_TYPE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APPT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APPT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_OFFICE_ID = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_OFFICE_ID = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_INTERVAL = 1;
    private static final Integer UPDATED_INTERVAL = 2;

    private static final LocalDate DEFAULT_START_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TOTAL_QUOTA = 1;
    private static final Integer UPDATED_TOTAL_QUOTA = 2;

    private static final Integer DEFAULT_AVA_QUOTA = 1;
    private static final Integer UPDATED_AVA_QUOTA = 2;

    private static final Integer DEFAULT_USED_QUOTA = 1;
    private static final Integer UPDATED_USED_QUOTA = 2;

    private static final Integer DEFAULT_FREEZE_QUOTA = 1;
    private static final Integer UPDATED_FREEZE_QUOTA = 2;

    private static final Integer DEFAULT_VIR_QUOTA_USED = 1;
    private static final Integer UPDATED_VIR_QUOTA_USED = 2;

    private static final LocalDate DEFAULT_CREATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LAST_UPD_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_UPD_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LAST_UPD_BY = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPD_BY = "BBBBBBBBBB";

    @Autowired
    private QuotaPlanRepository quotaPlanRepository;

    @Autowired
    private QuotaPlanMapper quotaPlanMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restQuotaPlanMockMvc;

    private QuotaPlan quotaPlan;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final QuotaPlanResource quotaPlanResource = new QuotaPlanResource(quotaPlanRepository, quotaPlanMapper);
        this.restQuotaPlanMockMvc = MockMvcBuilders.standaloneSetup(quotaPlanResource)
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
    public static QuotaPlan createEntity(EntityManager em) {
        QuotaPlan quotaPlan = new QuotaPlan()
            .quotaId(DEFAULT_QUOTA_ID)
            .apptType(DEFAULT_APPT_TYPE)
            .apptDate(DEFAULT_APPT_DATE)
            .officeId(DEFAULT_OFFICE_ID)
            .interval(DEFAULT_INTERVAL)
            .startTime(DEFAULT_START_TIME)
            .endTime(DEFAULT_END_TIME)
            .totalQuota(DEFAULT_TOTAL_QUOTA)
            .avaQuota(DEFAULT_AVA_QUOTA)
            .usedQuota(DEFAULT_USED_QUOTA)
            .freezeQuota(DEFAULT_FREEZE_QUOTA)
            .virQuotaUsed(DEFAULT_VIR_QUOTA_USED)
            .createDate(DEFAULT_CREATE_DATE)
            .createBy(DEFAULT_CREATE_BY)
            .lastUpdDate(DEFAULT_LAST_UPD_DATE)
            .lastUpdBy(DEFAULT_LAST_UPD_BY);
        return quotaPlan;
    }

    @Before
    public void initTest() {
        quotaPlan = createEntity(em);
    }

    @Test
    @Transactional
    public void createQuotaPlan() throws Exception {
        int databaseSizeBeforeCreate = quotaPlanRepository.findAll().size();

        // Create the QuotaPlan
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);
        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isCreated());

        // Validate the QuotaPlan in the database
        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeCreate + 1);
        QuotaPlan testQuotaPlan = quotaPlanList.get(quotaPlanList.size() - 1);
        assertThat(testQuotaPlan.getQuotaId()).isEqualTo(DEFAULT_QUOTA_ID);
        assertThat(testQuotaPlan.getApptType()).isEqualTo(DEFAULT_APPT_TYPE);
        assertThat(testQuotaPlan.getApptDate()).isEqualTo(DEFAULT_APPT_DATE);
        assertThat(testQuotaPlan.getOfficeId()).isEqualTo(DEFAULT_OFFICE_ID);
        assertThat(testQuotaPlan.getInterval()).isEqualTo(DEFAULT_INTERVAL);
        assertThat(testQuotaPlan.getStartTime()).isEqualTo(DEFAULT_START_TIME);
        assertThat(testQuotaPlan.getEndTime()).isEqualTo(DEFAULT_END_TIME);
        assertThat(testQuotaPlan.getTotalQuota()).isEqualTo(DEFAULT_TOTAL_QUOTA);
        assertThat(testQuotaPlan.getAvaQuota()).isEqualTo(DEFAULT_AVA_QUOTA);
        assertThat(testQuotaPlan.getUsedQuota()).isEqualTo(DEFAULT_USED_QUOTA);
        assertThat(testQuotaPlan.getFreezeQuota()).isEqualTo(DEFAULT_FREEZE_QUOTA);
        assertThat(testQuotaPlan.getVirQuotaUsed()).isEqualTo(DEFAULT_VIR_QUOTA_USED);
        assertThat(testQuotaPlan.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testQuotaPlan.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testQuotaPlan.getLastUpdDate()).isEqualTo(DEFAULT_LAST_UPD_DATE);
        assertThat(testQuotaPlan.getLastUpdBy()).isEqualTo(DEFAULT_LAST_UPD_BY);
    }

    @Test
    @Transactional
    public void createQuotaPlanWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = quotaPlanRepository.findAll().size();

        // Create the QuotaPlan with an existing ID
        quotaPlan.setId(1L);
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        // An entity with an existing ID cannot be created, so this API call must fail
        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        // Validate the QuotaPlan in the database
        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkQuotaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setQuotaId(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApptTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setApptType(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApptDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setApptDate(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkOfficeIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setOfficeId(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIntervalIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setInterval(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkStartTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setStartTime(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEndTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setEndTime(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTotalQuotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setTotalQuota(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAvaQuotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setAvaQuota(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkUsedQuotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setUsedQuota(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkFreezeQuotaIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setFreezeQuota(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVirQuotaUsedIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setVirQuotaUsed(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setCreateDate(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateByIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setCreateBy(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setLastUpdDate(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdByIsRequired() throws Exception {
        int databaseSizeBeforeTest = quotaPlanRepository.findAll().size();
        // set the field null
        quotaPlan.setLastUpdBy(null);

        // Create the QuotaPlan, which fails.
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        restQuotaPlanMockMvc.perform(post("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isBadRequest());

        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllQuotaPlans() throws Exception {
        // Initialize the database
        quotaPlanRepository.saveAndFlush(quotaPlan);

        // Get all the quotaPlanList
        restQuotaPlanMockMvc.perform(get("/api/quota-plans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(quotaPlan.getId().intValue())))
            .andExpect(jsonPath("$.[*].quotaId").value(hasItem(DEFAULT_QUOTA_ID.toString())))
            .andExpect(jsonPath("$.[*].apptType").value(hasItem(DEFAULT_APPT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].apptDate").value(hasItem(DEFAULT_APPT_DATE.toString())))
            .andExpect(jsonPath("$.[*].officeId").value(hasItem(DEFAULT_OFFICE_ID.toString())))
            .andExpect(jsonPath("$.[*].interval").value(hasItem(DEFAULT_INTERVAL)))
            .andExpect(jsonPath("$.[*].startTime").value(hasItem(DEFAULT_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].endTime").value(hasItem(DEFAULT_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].totalQuota").value(hasItem(DEFAULT_TOTAL_QUOTA)))
            .andExpect(jsonPath("$.[*].avaQuota").value(hasItem(DEFAULT_AVA_QUOTA)))
            .andExpect(jsonPath("$.[*].usedQuota").value(hasItem(DEFAULT_USED_QUOTA)))
            .andExpect(jsonPath("$.[*].freezeQuota").value(hasItem(DEFAULT_FREEZE_QUOTA)))
            .andExpect(jsonPath("$.[*].virQuotaUsed").value(hasItem(DEFAULT_VIR_QUOTA_USED)))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdDate").value(hasItem(DEFAULT_LAST_UPD_DATE.toString())))
            .andExpect(jsonPath("$.[*].lastUpdBy").value(hasItem(DEFAULT_LAST_UPD_BY.toString())));
    }

    @Test
    @Transactional
    public void getQuotaPlan() throws Exception {
        // Initialize the database
        quotaPlanRepository.saveAndFlush(quotaPlan);

        // Get the quotaPlan
        restQuotaPlanMockMvc.perform(get("/api/quota-plans/{id}", quotaPlan.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(quotaPlan.getId().intValue()))
            .andExpect(jsonPath("$.quotaId").value(DEFAULT_QUOTA_ID.toString()))
            .andExpect(jsonPath("$.apptType").value(DEFAULT_APPT_TYPE.toString()))
            .andExpect(jsonPath("$.apptDate").value(DEFAULT_APPT_DATE.toString()))
            .andExpect(jsonPath("$.officeId").value(DEFAULT_OFFICE_ID.toString()))
            .andExpect(jsonPath("$.interval").value(DEFAULT_INTERVAL))
            .andExpect(jsonPath("$.startTime").value(DEFAULT_START_TIME.toString()))
            .andExpect(jsonPath("$.endTime").value(DEFAULT_END_TIME.toString()))
            .andExpect(jsonPath("$.totalQuota").value(DEFAULT_TOTAL_QUOTA))
            .andExpect(jsonPath("$.avaQuota").value(DEFAULT_AVA_QUOTA))
            .andExpect(jsonPath("$.usedQuota").value(DEFAULT_USED_QUOTA))
            .andExpect(jsonPath("$.freezeQuota").value(DEFAULT_FREEZE_QUOTA))
            .andExpect(jsonPath("$.virQuotaUsed").value(DEFAULT_VIR_QUOTA_USED))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY.toString()))
            .andExpect(jsonPath("$.lastUpdDate").value(DEFAULT_LAST_UPD_DATE.toString()))
            .andExpect(jsonPath("$.lastUpdBy").value(DEFAULT_LAST_UPD_BY.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingQuotaPlan() throws Exception {
        // Get the quotaPlan
        restQuotaPlanMockMvc.perform(get("/api/quota-plans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateQuotaPlan() throws Exception {
        // Initialize the database
        quotaPlanRepository.saveAndFlush(quotaPlan);
        int databaseSizeBeforeUpdate = quotaPlanRepository.findAll().size();

        // Update the quotaPlan
        QuotaPlan updatedQuotaPlan = quotaPlanRepository.findOne(quotaPlan.getId());
        // Disconnect from session so that the updates on updatedQuotaPlan are not directly saved in db
        em.detach(updatedQuotaPlan);
        updatedQuotaPlan
            .quotaId(UPDATED_QUOTA_ID)
            .apptType(UPDATED_APPT_TYPE)
            .apptDate(UPDATED_APPT_DATE)
            .officeId(UPDATED_OFFICE_ID)
            .interval(UPDATED_INTERVAL)
            .startTime(UPDATED_START_TIME)
            .endTime(UPDATED_END_TIME)
            .totalQuota(UPDATED_TOTAL_QUOTA)
            .avaQuota(UPDATED_AVA_QUOTA)
            .usedQuota(UPDATED_USED_QUOTA)
            .freezeQuota(UPDATED_FREEZE_QUOTA)
            .virQuotaUsed(UPDATED_VIR_QUOTA_USED)
            .createDate(UPDATED_CREATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .lastUpdDate(UPDATED_LAST_UPD_DATE)
            .lastUpdBy(UPDATED_LAST_UPD_BY);
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(updatedQuotaPlan);

        restQuotaPlanMockMvc.perform(put("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isOk());

        // Validate the QuotaPlan in the database
        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeUpdate);
        QuotaPlan testQuotaPlan = quotaPlanList.get(quotaPlanList.size() - 1);
        assertThat(testQuotaPlan.getQuotaId()).isEqualTo(UPDATED_QUOTA_ID);
        assertThat(testQuotaPlan.getApptType()).isEqualTo(UPDATED_APPT_TYPE);
        assertThat(testQuotaPlan.getApptDate()).isEqualTo(UPDATED_APPT_DATE);
        assertThat(testQuotaPlan.getOfficeId()).isEqualTo(UPDATED_OFFICE_ID);
        assertThat(testQuotaPlan.getInterval()).isEqualTo(UPDATED_INTERVAL);
        assertThat(testQuotaPlan.getStartTime()).isEqualTo(UPDATED_START_TIME);
        assertThat(testQuotaPlan.getEndTime()).isEqualTo(UPDATED_END_TIME);
        assertThat(testQuotaPlan.getTotalQuota()).isEqualTo(UPDATED_TOTAL_QUOTA);
        assertThat(testQuotaPlan.getAvaQuota()).isEqualTo(UPDATED_AVA_QUOTA);
        assertThat(testQuotaPlan.getUsedQuota()).isEqualTo(UPDATED_USED_QUOTA);
        assertThat(testQuotaPlan.getFreezeQuota()).isEqualTo(UPDATED_FREEZE_QUOTA);
        assertThat(testQuotaPlan.getVirQuotaUsed()).isEqualTo(UPDATED_VIR_QUOTA_USED);
        assertThat(testQuotaPlan.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testQuotaPlan.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testQuotaPlan.getLastUpdDate()).isEqualTo(UPDATED_LAST_UPD_DATE);
        assertThat(testQuotaPlan.getLastUpdBy()).isEqualTo(UPDATED_LAST_UPD_BY);
    }

    @Test
    @Transactional
    public void updateNonExistingQuotaPlan() throws Exception {
        int databaseSizeBeforeUpdate = quotaPlanRepository.findAll().size();

        // Create the QuotaPlan
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restQuotaPlanMockMvc.perform(put("/api/quota-plans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(quotaPlanDTO)))
            .andExpect(status().isCreated());

        // Validate the QuotaPlan in the database
        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteQuotaPlan() throws Exception {
        // Initialize the database
        quotaPlanRepository.saveAndFlush(quotaPlan);
        int databaseSizeBeforeDelete = quotaPlanRepository.findAll().size();

        // Get the quotaPlan
        restQuotaPlanMockMvc.perform(delete("/api/quota-plans/{id}", quotaPlan.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<QuotaPlan> quotaPlanList = quotaPlanRepository.findAll();
        assertThat(quotaPlanList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuotaPlan.class);
        QuotaPlan quotaPlan1 = new QuotaPlan();
        quotaPlan1.setId(1L);
        QuotaPlan quotaPlan2 = new QuotaPlan();
        quotaPlan2.setId(quotaPlan1.getId());
        assertThat(quotaPlan1).isEqualTo(quotaPlan2);
        quotaPlan2.setId(2L);
        assertThat(quotaPlan1).isNotEqualTo(quotaPlan2);
        quotaPlan1.setId(null);
        assertThat(quotaPlan1).isNotEqualTo(quotaPlan2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(QuotaPlanDTO.class);
        QuotaPlanDTO quotaPlanDTO1 = new QuotaPlanDTO();
        quotaPlanDTO1.setId(1L);
        QuotaPlanDTO quotaPlanDTO2 = new QuotaPlanDTO();
        assertThat(quotaPlanDTO1).isNotEqualTo(quotaPlanDTO2);
        quotaPlanDTO2.setId(quotaPlanDTO1.getId());
        assertThat(quotaPlanDTO1).isEqualTo(quotaPlanDTO2);
        quotaPlanDTO2.setId(2L);
        assertThat(quotaPlanDTO1).isNotEqualTo(quotaPlanDTO2);
        quotaPlanDTO1.setId(null);
        assertThat(quotaPlanDTO1).isNotEqualTo(quotaPlanDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(quotaPlanMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(quotaPlanMapper.fromId(null)).isNull();
    }
}
