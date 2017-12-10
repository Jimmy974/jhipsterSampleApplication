package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.AppointmentTrans;
import io.github.jhipster.application.repository.AppointmentTransRepository;
import io.github.jhipster.application.service.dto.AppointmentTransDTO;
import io.github.jhipster.application.service.mapper.AppointmentTransMapper;
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
 * Test class for the AppointmentTransResource REST controller.
 *
 * @see AppointmentTransResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AppointmentTransResourceIntTest {

    private static final Long DEFAULT_ERN = 1L;
    private static final Long UPDATED_ERN = 2L;

    private static final Long DEFAULT_TRN = 1L;
    private static final Long UPDATED_TRN = 2L;

    private static final Long DEFAULT_PRE_ERN = 1L;
    private static final Long UPDATED_PRE_ERN = 2L;

    private static final Long DEFAULT_PRE_TRN = 1L;
    private static final Long UPDATED_PRE_TRN = 2L;

    private static final Integer DEFAULT_APP_ID = 1;
    private static final Integer UPDATED_APP_ID = 2;

    private static final String DEFAULT_EXPORT_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_EXPORT_STATUS = "BBBBBBBBBB";

    private static final String DEFAULT_INTERFACE_FILE = "AAAAAAAAAA";
    private static final String UPDATED_INTERFACE_FILE = "BBBBBBBBBB";

    private static final String DEFAULT_CONTROL_FILE = "AAAAAAAAAA";
    private static final String UPDATED_CONTROL_FILE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_EXPORT_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_EXPORT_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_ENQ_CODE = 1;
    private static final Integer UPDATED_ENQ_CODE = 2;

    private static final LocalDate DEFAULT_TRANS_TIME = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TRANS_TIME = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_COMM_CHANNEL = "AAAAAAAAAA";
    private static final String UPDATED_COMM_CHANNEL = "BBBBBBBBBB";

    private static final String DEFAULT_COMM_PARAMETER = "AAAAAAAAAA";
    private static final String UPDATED_COMM_PARAMETER = "BBBBBBBBBB";

    private static final String DEFAULT_IS_NOT_SENT = "AAAAAAAAAA";
    private static final String UPDATED_IS_NOT_SENT = "BBBBBBBBBB";

    private static final String DEFAULT_IS_REM_SENT = "AAAAAAAAAA";
    private static final String UPDATED_IS_REM_SENT = "BBBBBBBBBB";

    private static final String DEFAULT_VIRTUAL_IND = "AAAAAAAAAA";
    private static final String UPDATED_VIRTUAL_IND = "BBBBBBBBBB";

    private static final String DEFAULT_ACTION = "AAAAAAAAAA";
    private static final String UPDATED_ACTION = "BBBBBBBBBB";

    private static final String DEFAULT_ACTIVE = "AAAAAAAAAA";
    private static final String UPDATED_ACTIVE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATE_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATE_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATE_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATE_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_LAST_UPD_BY = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_UPD_BY = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_LAST_UPD_DATE = "AAAAAAAAAA";
    private static final String UPDATED_LAST_UPD_DATE = "BBBBBBBBBB";

    @Autowired
    private AppointmentTransRepository appointmentTransRepository;

    @Autowired
    private AppointmentTransMapper appointmentTransMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAppointmentTransMockMvc;

    private AppointmentTrans appointmentTrans;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppointmentTransResource appointmentTransResource = new AppointmentTransResource(appointmentTransRepository, appointmentTransMapper);
        this.restAppointmentTransMockMvc = MockMvcBuilders.standaloneSetup(appointmentTransResource)
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
    public static AppointmentTrans createEntity(EntityManager em) {
        AppointmentTrans appointmentTrans = new AppointmentTrans()
            .ern(DEFAULT_ERN)
            .trn(DEFAULT_TRN)
            .preErn(DEFAULT_PRE_ERN)
            .preTrn(DEFAULT_PRE_TRN)
            .appId(DEFAULT_APP_ID)
            .exportStatus(DEFAULT_EXPORT_STATUS)
            .interfaceFile(DEFAULT_INTERFACE_FILE)
            .controlFile(DEFAULT_CONTROL_FILE)
            .exportTime(DEFAULT_EXPORT_TIME)
            .enqCode(DEFAULT_ENQ_CODE)
            .transTime(DEFAULT_TRANS_TIME)
            .commChannel(DEFAULT_COMM_CHANNEL)
            .commParameter(DEFAULT_COMM_PARAMETER)
            .isNotSent(DEFAULT_IS_NOT_SENT)
            .isRemSent(DEFAULT_IS_REM_SENT)
            .virtualInd(DEFAULT_VIRTUAL_IND)
            .action(DEFAULT_ACTION)
            .active(DEFAULT_ACTIVE)
            .createDate(DEFAULT_CREATE_DATE)
            .createBy(DEFAULT_CREATE_BY)
            .lastUpdBy(DEFAULT_LAST_UPD_BY)
            .lastUpdDate(DEFAULT_LAST_UPD_DATE);
        return appointmentTrans;
    }

    @Before
    public void initTest() {
        appointmentTrans = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppointmentTrans() throws Exception {
        int databaseSizeBeforeCreate = appointmentTransRepository.findAll().size();

        // Create the AppointmentTrans
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);
        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isCreated());

        // Validate the AppointmentTrans in the database
        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeCreate + 1);
        AppointmentTrans testAppointmentTrans = appointmentTransList.get(appointmentTransList.size() - 1);
        assertThat(testAppointmentTrans.getErn()).isEqualTo(DEFAULT_ERN);
        assertThat(testAppointmentTrans.getTrn()).isEqualTo(DEFAULT_TRN);
        assertThat(testAppointmentTrans.getPreErn()).isEqualTo(DEFAULT_PRE_ERN);
        assertThat(testAppointmentTrans.getPreTrn()).isEqualTo(DEFAULT_PRE_TRN);
        assertThat(testAppointmentTrans.getAppId()).isEqualTo(DEFAULT_APP_ID);
        assertThat(testAppointmentTrans.getExportStatus()).isEqualTo(DEFAULT_EXPORT_STATUS);
        assertThat(testAppointmentTrans.getInterfaceFile()).isEqualTo(DEFAULT_INTERFACE_FILE);
        assertThat(testAppointmentTrans.getControlFile()).isEqualTo(DEFAULT_CONTROL_FILE);
        assertThat(testAppointmentTrans.getExportTime()).isEqualTo(DEFAULT_EXPORT_TIME);
        assertThat(testAppointmentTrans.getEnqCode()).isEqualTo(DEFAULT_ENQ_CODE);
        assertThat(testAppointmentTrans.getTransTime()).isEqualTo(DEFAULT_TRANS_TIME);
        assertThat(testAppointmentTrans.getCommChannel()).isEqualTo(DEFAULT_COMM_CHANNEL);
        assertThat(testAppointmentTrans.getCommParameter()).isEqualTo(DEFAULT_COMM_PARAMETER);
        assertThat(testAppointmentTrans.getIsNotSent()).isEqualTo(DEFAULT_IS_NOT_SENT);
        assertThat(testAppointmentTrans.getIsRemSent()).isEqualTo(DEFAULT_IS_REM_SENT);
        assertThat(testAppointmentTrans.getVirtualInd()).isEqualTo(DEFAULT_VIRTUAL_IND);
        assertThat(testAppointmentTrans.getAction()).isEqualTo(DEFAULT_ACTION);
        assertThat(testAppointmentTrans.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testAppointmentTrans.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAppointmentTrans.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testAppointmentTrans.getLastUpdBy()).isEqualTo(DEFAULT_LAST_UPD_BY);
        assertThat(testAppointmentTrans.getLastUpdDate()).isEqualTo(DEFAULT_LAST_UPD_DATE);
    }

    @Test
    @Transactional
    public void createAppointmentTransWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appointmentTransRepository.findAll().size();

        // Create the AppointmentTrans with an existing ID
        appointmentTrans.setId(1L);
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppointmentTrans in the database
        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkErnIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setErn(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTrnIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setTrn(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAppIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setAppId(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEnqCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setEnqCode(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkTransTimeIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setTransTime(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCommChannelIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setCommChannel(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsNotSentIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setIsNotSent(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkIsRemSentIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setIsRemSent(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkVirtualIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setVirtualInd(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActionIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setAction(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setActive(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setCreateDate(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateByIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setCreateBy(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdByIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setLastUpdBy(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentTransRepository.findAll().size();
        // set the field null
        appointmentTrans.setLastUpdDate(null);

        // Create the AppointmentTrans, which fails.
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        restAppointmentTransMockMvc.perform(post("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAppointmentTrans() throws Exception {
        // Initialize the database
        appointmentTransRepository.saveAndFlush(appointmentTrans);

        // Get all the appointmentTransList
        restAppointmentTransMockMvc.perform(get("/api/appointment-trans?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appointmentTrans.getId().intValue())))
            .andExpect(jsonPath("$.[*].ern").value(hasItem(DEFAULT_ERN.intValue())))
            .andExpect(jsonPath("$.[*].trn").value(hasItem(DEFAULT_TRN.intValue())))
            .andExpect(jsonPath("$.[*].preErn").value(hasItem(DEFAULT_PRE_ERN.intValue())))
            .andExpect(jsonPath("$.[*].preTrn").value(hasItem(DEFAULT_PRE_TRN.intValue())))
            .andExpect(jsonPath("$.[*].appId").value(hasItem(DEFAULT_APP_ID)))
            .andExpect(jsonPath("$.[*].exportStatus").value(hasItem(DEFAULT_EXPORT_STATUS.toString())))
            .andExpect(jsonPath("$.[*].interfaceFile").value(hasItem(DEFAULT_INTERFACE_FILE.toString())))
            .andExpect(jsonPath("$.[*].controlFile").value(hasItem(DEFAULT_CONTROL_FILE.toString())))
            .andExpect(jsonPath("$.[*].exportTime").value(hasItem(DEFAULT_EXPORT_TIME.toString())))
            .andExpect(jsonPath("$.[*].enqCode").value(hasItem(DEFAULT_ENQ_CODE)))
            .andExpect(jsonPath("$.[*].transTime").value(hasItem(DEFAULT_TRANS_TIME.toString())))
            .andExpect(jsonPath("$.[*].commChannel").value(hasItem(DEFAULT_COMM_CHANNEL.toString())))
            .andExpect(jsonPath("$.[*].commParameter").value(hasItem(DEFAULT_COMM_PARAMETER.toString())))
            .andExpect(jsonPath("$.[*].isNotSent").value(hasItem(DEFAULT_IS_NOT_SENT.toString())))
            .andExpect(jsonPath("$.[*].isRemSent").value(hasItem(DEFAULT_IS_REM_SENT.toString())))
            .andExpect(jsonPath("$.[*].virtualInd").value(hasItem(DEFAULT_VIRTUAL_IND.toString())))
            .andExpect(jsonPath("$.[*].action").value(hasItem(DEFAULT_ACTION.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdBy").value(hasItem(DEFAULT_LAST_UPD_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdDate").value(hasItem(DEFAULT_LAST_UPD_DATE.toString())));
    }

    @Test
    @Transactional
    public void getAppointmentTrans() throws Exception {
        // Initialize the database
        appointmentTransRepository.saveAndFlush(appointmentTrans);

        // Get the appointmentTrans
        restAppointmentTransMockMvc.perform(get("/api/appointment-trans/{id}", appointmentTrans.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(appointmentTrans.getId().intValue()))
            .andExpect(jsonPath("$.ern").value(DEFAULT_ERN.intValue()))
            .andExpect(jsonPath("$.trn").value(DEFAULT_TRN.intValue()))
            .andExpect(jsonPath("$.preErn").value(DEFAULT_PRE_ERN.intValue()))
            .andExpect(jsonPath("$.preTrn").value(DEFAULT_PRE_TRN.intValue()))
            .andExpect(jsonPath("$.appId").value(DEFAULT_APP_ID))
            .andExpect(jsonPath("$.exportStatus").value(DEFAULT_EXPORT_STATUS.toString()))
            .andExpect(jsonPath("$.interfaceFile").value(DEFAULT_INTERFACE_FILE.toString()))
            .andExpect(jsonPath("$.controlFile").value(DEFAULT_CONTROL_FILE.toString()))
            .andExpect(jsonPath("$.exportTime").value(DEFAULT_EXPORT_TIME.toString()))
            .andExpect(jsonPath("$.enqCode").value(DEFAULT_ENQ_CODE))
            .andExpect(jsonPath("$.transTime").value(DEFAULT_TRANS_TIME.toString()))
            .andExpect(jsonPath("$.commChannel").value(DEFAULT_COMM_CHANNEL.toString()))
            .andExpect(jsonPath("$.commParameter").value(DEFAULT_COMM_PARAMETER.toString()))
            .andExpect(jsonPath("$.isNotSent").value(DEFAULT_IS_NOT_SENT.toString()))
            .andExpect(jsonPath("$.isRemSent").value(DEFAULT_IS_REM_SENT.toString()))
            .andExpect(jsonPath("$.virtualInd").value(DEFAULT_VIRTUAL_IND.toString()))
            .andExpect(jsonPath("$.action").value(DEFAULT_ACTION.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY.toString()))
            .andExpect(jsonPath("$.lastUpdBy").value(DEFAULT_LAST_UPD_BY.toString()))
            .andExpect(jsonPath("$.lastUpdDate").value(DEFAULT_LAST_UPD_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAppointmentTrans() throws Exception {
        // Get the appointmentTrans
        restAppointmentTransMockMvc.perform(get("/api/appointment-trans/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppointmentTrans() throws Exception {
        // Initialize the database
        appointmentTransRepository.saveAndFlush(appointmentTrans);
        int databaseSizeBeforeUpdate = appointmentTransRepository.findAll().size();

        // Update the appointmentTrans
        AppointmentTrans updatedAppointmentTrans = appointmentTransRepository.findOne(appointmentTrans.getId());
        // Disconnect from session so that the updates on updatedAppointmentTrans are not directly saved in db
        em.detach(updatedAppointmentTrans);
        updatedAppointmentTrans
            .ern(UPDATED_ERN)
            .trn(UPDATED_TRN)
            .preErn(UPDATED_PRE_ERN)
            .preTrn(UPDATED_PRE_TRN)
            .appId(UPDATED_APP_ID)
            .exportStatus(UPDATED_EXPORT_STATUS)
            .interfaceFile(UPDATED_INTERFACE_FILE)
            .controlFile(UPDATED_CONTROL_FILE)
            .exportTime(UPDATED_EXPORT_TIME)
            .enqCode(UPDATED_ENQ_CODE)
            .transTime(UPDATED_TRANS_TIME)
            .commChannel(UPDATED_COMM_CHANNEL)
            .commParameter(UPDATED_COMM_PARAMETER)
            .isNotSent(UPDATED_IS_NOT_SENT)
            .isRemSent(UPDATED_IS_REM_SENT)
            .virtualInd(UPDATED_VIRTUAL_IND)
            .action(UPDATED_ACTION)
            .active(UPDATED_ACTIVE)
            .createDate(UPDATED_CREATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .lastUpdBy(UPDATED_LAST_UPD_BY)
            .lastUpdDate(UPDATED_LAST_UPD_DATE);
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(updatedAppointmentTrans);

        restAppointmentTransMockMvc.perform(put("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isOk());

        // Validate the AppointmentTrans in the database
        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeUpdate);
        AppointmentTrans testAppointmentTrans = appointmentTransList.get(appointmentTransList.size() - 1);
        assertThat(testAppointmentTrans.getErn()).isEqualTo(UPDATED_ERN);
        assertThat(testAppointmentTrans.getTrn()).isEqualTo(UPDATED_TRN);
        assertThat(testAppointmentTrans.getPreErn()).isEqualTo(UPDATED_PRE_ERN);
        assertThat(testAppointmentTrans.getPreTrn()).isEqualTo(UPDATED_PRE_TRN);
        assertThat(testAppointmentTrans.getAppId()).isEqualTo(UPDATED_APP_ID);
        assertThat(testAppointmentTrans.getExportStatus()).isEqualTo(UPDATED_EXPORT_STATUS);
        assertThat(testAppointmentTrans.getInterfaceFile()).isEqualTo(UPDATED_INTERFACE_FILE);
        assertThat(testAppointmentTrans.getControlFile()).isEqualTo(UPDATED_CONTROL_FILE);
        assertThat(testAppointmentTrans.getExportTime()).isEqualTo(UPDATED_EXPORT_TIME);
        assertThat(testAppointmentTrans.getEnqCode()).isEqualTo(UPDATED_ENQ_CODE);
        assertThat(testAppointmentTrans.getTransTime()).isEqualTo(UPDATED_TRANS_TIME);
        assertThat(testAppointmentTrans.getCommChannel()).isEqualTo(UPDATED_COMM_CHANNEL);
        assertThat(testAppointmentTrans.getCommParameter()).isEqualTo(UPDATED_COMM_PARAMETER);
        assertThat(testAppointmentTrans.getIsNotSent()).isEqualTo(UPDATED_IS_NOT_SENT);
        assertThat(testAppointmentTrans.getIsRemSent()).isEqualTo(UPDATED_IS_REM_SENT);
        assertThat(testAppointmentTrans.getVirtualInd()).isEqualTo(UPDATED_VIRTUAL_IND);
        assertThat(testAppointmentTrans.getAction()).isEqualTo(UPDATED_ACTION);
        assertThat(testAppointmentTrans.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testAppointmentTrans.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAppointmentTrans.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testAppointmentTrans.getLastUpdBy()).isEqualTo(UPDATED_LAST_UPD_BY);
        assertThat(testAppointmentTrans.getLastUpdDate()).isEqualTo(UPDATED_LAST_UPD_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingAppointmentTrans() throws Exception {
        int databaseSizeBeforeUpdate = appointmentTransRepository.findAll().size();

        // Create the AppointmentTrans
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAppointmentTransMockMvc.perform(put("/api/appointment-trans")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentTransDTO)))
            .andExpect(status().isCreated());

        // Validate the AppointmentTrans in the database
        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAppointmentTrans() throws Exception {
        // Initialize the database
        appointmentTransRepository.saveAndFlush(appointmentTrans);
        int databaseSizeBeforeDelete = appointmentTransRepository.findAll().size();

        // Get the appointmentTrans
        restAppointmentTransMockMvc.perform(delete("/api/appointment-trans/{id}", appointmentTrans.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AppointmentTrans> appointmentTransList = appointmentTransRepository.findAll();
        assertThat(appointmentTransList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppointmentTrans.class);
        AppointmentTrans appointmentTrans1 = new AppointmentTrans();
        appointmentTrans1.setId(1L);
        AppointmentTrans appointmentTrans2 = new AppointmentTrans();
        appointmentTrans2.setId(appointmentTrans1.getId());
        assertThat(appointmentTrans1).isEqualTo(appointmentTrans2);
        appointmentTrans2.setId(2L);
        assertThat(appointmentTrans1).isNotEqualTo(appointmentTrans2);
        appointmentTrans1.setId(null);
        assertThat(appointmentTrans1).isNotEqualTo(appointmentTrans2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppointmentTransDTO.class);
        AppointmentTransDTO appointmentTransDTO1 = new AppointmentTransDTO();
        appointmentTransDTO1.setId(1L);
        AppointmentTransDTO appointmentTransDTO2 = new AppointmentTransDTO();
        assertThat(appointmentTransDTO1).isNotEqualTo(appointmentTransDTO2);
        appointmentTransDTO2.setId(appointmentTransDTO1.getId());
        assertThat(appointmentTransDTO1).isEqualTo(appointmentTransDTO2);
        appointmentTransDTO2.setId(2L);
        assertThat(appointmentTransDTO1).isNotEqualTo(appointmentTransDTO2);
        appointmentTransDTO1.setId(null);
        assertThat(appointmentTransDTO1).isNotEqualTo(appointmentTransDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(appointmentTransMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(appointmentTransMapper.fromId(null)).isNull();
    }
}
