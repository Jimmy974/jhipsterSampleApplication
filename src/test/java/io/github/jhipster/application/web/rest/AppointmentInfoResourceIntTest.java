package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterSampleApplicationApp;

import io.github.jhipster.application.domain.AppointmentInfo;
import io.github.jhipster.application.repository.AppointmentInfoRepository;
import io.github.jhipster.application.service.dto.AppointmentInfoDTO;
import io.github.jhipster.application.service.mapper.AppointmentInfoMapper;
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
 * Test class for the AppointmentInfoResource REST controller.
 *
 * @see AppointmentInfoResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterSampleApplicationApp.class)
public class AppointmentInfoResourceIntTest {

    private static final Long DEFAULT_TRN = 1L;
    private static final Long UPDATED_TRN = 2L;

    private static final String DEFAULT_GROUP_MEM_ID = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_MEM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_QUOTA_ID = "AAAAAAAAAA";
    private static final String UPDATED_QUOTA_ID = "BBBBBBBBBB";

    private static final String DEFAULT_SERVICE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICE_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_APMID_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_APMID_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_APMID_CODE = "AAAAAAAAAA";
    private static final String UPDATED_APMID_CODE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_APP_DOB = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_APP_DOB = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SERVICES_NATURE = "AAAAAAAAAA";
    private static final String UPDATED_SERVICES_NATURE = "BBBBBBBBBB";

    private static final String DEFAULT_AGE_IND = "AAAAAAAAAA";
    private static final String UPDATED_AGE_IND = "BBBBBBBBBB";

    private static final String DEFAULT_PREFIL_IND = "AAAAAAAAAA";
    private static final String UPDATED_PREFIL_IND = "BBBBBBBBBB";

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
    private AppointmentInfoRepository appointmentInfoRepository;

    @Autowired
    private AppointmentInfoMapper appointmentInfoMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restAppointmentInfoMockMvc;

    private AppointmentInfo appointmentInfo;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AppointmentInfoResource appointmentInfoResource = new AppointmentInfoResource(appointmentInfoRepository, appointmentInfoMapper);
        this.restAppointmentInfoMockMvc = MockMvcBuilders.standaloneSetup(appointmentInfoResource)
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
    public static AppointmentInfo createEntity(EntityManager em) {
        AppointmentInfo appointmentInfo = new AppointmentInfo()
            .trn(DEFAULT_TRN)
            .groupMemId(DEFAULT_GROUP_MEM_ID)
            .quotaId(DEFAULT_QUOTA_ID)
            .serviceType(DEFAULT_SERVICE_TYPE)
            .apmidType(DEFAULT_APMID_TYPE)
            .apmidCode(DEFAULT_APMID_CODE)
            .appDob(DEFAULT_APP_DOB)
            .servicesNature(DEFAULT_SERVICES_NATURE)
            .ageInd(DEFAULT_AGE_IND)
            .prefilInd(DEFAULT_PREFIL_IND)
            .active(DEFAULT_ACTIVE)
            .createDate(DEFAULT_CREATE_DATE)
            .createBy(DEFAULT_CREATE_BY)
            .lastUpdBy(DEFAULT_LAST_UPD_BY)
            .lastUpdDate(DEFAULT_LAST_UPD_DATE);
        return appointmentInfo;
    }

    @Before
    public void initTest() {
        appointmentInfo = createEntity(em);
    }

    @Test
    @Transactional
    public void createAppointmentInfo() throws Exception {
        int databaseSizeBeforeCreate = appointmentInfoRepository.findAll().size();

        // Create the AppointmentInfo
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);
        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the AppointmentInfo in the database
        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeCreate + 1);
        AppointmentInfo testAppointmentInfo = appointmentInfoList.get(appointmentInfoList.size() - 1);
        assertThat(testAppointmentInfo.getTrn()).isEqualTo(DEFAULT_TRN);
        assertThat(testAppointmentInfo.getGroupMemId()).isEqualTo(DEFAULT_GROUP_MEM_ID);
        assertThat(testAppointmentInfo.getQuotaId()).isEqualTo(DEFAULT_QUOTA_ID);
        assertThat(testAppointmentInfo.getServiceType()).isEqualTo(DEFAULT_SERVICE_TYPE);
        assertThat(testAppointmentInfo.getApmidType()).isEqualTo(DEFAULT_APMID_TYPE);
        assertThat(testAppointmentInfo.getApmidCode()).isEqualTo(DEFAULT_APMID_CODE);
        assertThat(testAppointmentInfo.getAppDob()).isEqualTo(DEFAULT_APP_DOB);
        assertThat(testAppointmentInfo.getServicesNature()).isEqualTo(DEFAULT_SERVICES_NATURE);
        assertThat(testAppointmentInfo.getAgeInd()).isEqualTo(DEFAULT_AGE_IND);
        assertThat(testAppointmentInfo.getPrefilInd()).isEqualTo(DEFAULT_PREFIL_IND);
        assertThat(testAppointmentInfo.getActive()).isEqualTo(DEFAULT_ACTIVE);
        assertThat(testAppointmentInfo.getCreateDate()).isEqualTo(DEFAULT_CREATE_DATE);
        assertThat(testAppointmentInfo.getCreateBy()).isEqualTo(DEFAULT_CREATE_BY);
        assertThat(testAppointmentInfo.getLastUpdBy()).isEqualTo(DEFAULT_LAST_UPD_BY);
        assertThat(testAppointmentInfo.getLastUpdDate()).isEqualTo(DEFAULT_LAST_UPD_DATE);
    }

    @Test
    @Transactional
    public void createAppointmentInfoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = appointmentInfoRepository.findAll().size();

        // Create the AppointmentInfo with an existing ID
        appointmentInfo.setId(1L);
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        // Validate the AppointmentInfo in the database
        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void checkTrnIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setTrn(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkGroupMemIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setGroupMemId(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkQuotaIdIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setQuotaId(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkServiceTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setServiceType(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApmidTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setApmidType(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkApmidCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setApmidCode(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkServicesNatureIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setServicesNature(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAgeIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setAgeInd(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkPrefilIndIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setPrefilInd(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkActiveIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setActive(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setCreateDate(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkCreateByIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setCreateBy(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdByIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setLastUpdBy(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLastUpdDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = appointmentInfoRepository.findAll().size();
        // set the field null
        appointmentInfo.setLastUpdDate(null);

        // Create the AppointmentInfo, which fails.
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        restAppointmentInfoMockMvc.perform(post("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isBadRequest());

        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllAppointmentInfos() throws Exception {
        // Initialize the database
        appointmentInfoRepository.saveAndFlush(appointmentInfo);

        // Get all the appointmentInfoList
        restAppointmentInfoMockMvc.perform(get("/api/appointment-infos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(appointmentInfo.getId().intValue())))
            .andExpect(jsonPath("$.[*].trn").value(hasItem(DEFAULT_TRN.intValue())))
            .andExpect(jsonPath("$.[*].groupMemId").value(hasItem(DEFAULT_GROUP_MEM_ID.toString())))
            .andExpect(jsonPath("$.[*].quotaId").value(hasItem(DEFAULT_QUOTA_ID.toString())))
            .andExpect(jsonPath("$.[*].serviceType").value(hasItem(DEFAULT_SERVICE_TYPE.toString())))
            .andExpect(jsonPath("$.[*].apmidType").value(hasItem(DEFAULT_APMID_TYPE.toString())))
            .andExpect(jsonPath("$.[*].apmidCode").value(hasItem(DEFAULT_APMID_CODE.toString())))
            .andExpect(jsonPath("$.[*].appDob").value(hasItem(DEFAULT_APP_DOB.toString())))
            .andExpect(jsonPath("$.[*].servicesNature").value(hasItem(DEFAULT_SERVICES_NATURE.toString())))
            .andExpect(jsonPath("$.[*].ageInd").value(hasItem(DEFAULT_AGE_IND.toString())))
            .andExpect(jsonPath("$.[*].prefilInd").value(hasItem(DEFAULT_PREFIL_IND.toString())))
            .andExpect(jsonPath("$.[*].active").value(hasItem(DEFAULT_ACTIVE.toString())))
            .andExpect(jsonPath("$.[*].createDate").value(hasItem(DEFAULT_CREATE_DATE.toString())))
            .andExpect(jsonPath("$.[*].createBy").value(hasItem(DEFAULT_CREATE_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdBy").value(hasItem(DEFAULT_LAST_UPD_BY.toString())))
            .andExpect(jsonPath("$.[*].lastUpdDate").value(hasItem(DEFAULT_LAST_UPD_DATE.toString())));
    }

    @Test
    @Transactional
    public void getAppointmentInfo() throws Exception {
        // Initialize the database
        appointmentInfoRepository.saveAndFlush(appointmentInfo);

        // Get the appointmentInfo
        restAppointmentInfoMockMvc.perform(get("/api/appointment-infos/{id}", appointmentInfo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(appointmentInfo.getId().intValue()))
            .andExpect(jsonPath("$.trn").value(DEFAULT_TRN.intValue()))
            .andExpect(jsonPath("$.groupMemId").value(DEFAULT_GROUP_MEM_ID.toString()))
            .andExpect(jsonPath("$.quotaId").value(DEFAULT_QUOTA_ID.toString()))
            .andExpect(jsonPath("$.serviceType").value(DEFAULT_SERVICE_TYPE.toString()))
            .andExpect(jsonPath("$.apmidType").value(DEFAULT_APMID_TYPE.toString()))
            .andExpect(jsonPath("$.apmidCode").value(DEFAULT_APMID_CODE.toString()))
            .andExpect(jsonPath("$.appDob").value(DEFAULT_APP_DOB.toString()))
            .andExpect(jsonPath("$.servicesNature").value(DEFAULT_SERVICES_NATURE.toString()))
            .andExpect(jsonPath("$.ageInd").value(DEFAULT_AGE_IND.toString()))
            .andExpect(jsonPath("$.prefilInd").value(DEFAULT_PREFIL_IND.toString()))
            .andExpect(jsonPath("$.active").value(DEFAULT_ACTIVE.toString()))
            .andExpect(jsonPath("$.createDate").value(DEFAULT_CREATE_DATE.toString()))
            .andExpect(jsonPath("$.createBy").value(DEFAULT_CREATE_BY.toString()))
            .andExpect(jsonPath("$.lastUpdBy").value(DEFAULT_LAST_UPD_BY.toString()))
            .andExpect(jsonPath("$.lastUpdDate").value(DEFAULT_LAST_UPD_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingAppointmentInfo() throws Exception {
        // Get the appointmentInfo
        restAppointmentInfoMockMvc.perform(get("/api/appointment-infos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAppointmentInfo() throws Exception {
        // Initialize the database
        appointmentInfoRepository.saveAndFlush(appointmentInfo);
        int databaseSizeBeforeUpdate = appointmentInfoRepository.findAll().size();

        // Update the appointmentInfo
        AppointmentInfo updatedAppointmentInfo = appointmentInfoRepository.findOne(appointmentInfo.getId());
        // Disconnect from session so that the updates on updatedAppointmentInfo are not directly saved in db
        em.detach(updatedAppointmentInfo);
        updatedAppointmentInfo
            .trn(UPDATED_TRN)
            .groupMemId(UPDATED_GROUP_MEM_ID)
            .quotaId(UPDATED_QUOTA_ID)
            .serviceType(UPDATED_SERVICE_TYPE)
            .apmidType(UPDATED_APMID_TYPE)
            .apmidCode(UPDATED_APMID_CODE)
            .appDob(UPDATED_APP_DOB)
            .servicesNature(UPDATED_SERVICES_NATURE)
            .ageInd(UPDATED_AGE_IND)
            .prefilInd(UPDATED_PREFIL_IND)
            .active(UPDATED_ACTIVE)
            .createDate(UPDATED_CREATE_DATE)
            .createBy(UPDATED_CREATE_BY)
            .lastUpdBy(UPDATED_LAST_UPD_BY)
            .lastUpdDate(UPDATED_LAST_UPD_DATE);
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(updatedAppointmentInfo);

        restAppointmentInfoMockMvc.perform(put("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isOk());

        // Validate the AppointmentInfo in the database
        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeUpdate);
        AppointmentInfo testAppointmentInfo = appointmentInfoList.get(appointmentInfoList.size() - 1);
        assertThat(testAppointmentInfo.getTrn()).isEqualTo(UPDATED_TRN);
        assertThat(testAppointmentInfo.getGroupMemId()).isEqualTo(UPDATED_GROUP_MEM_ID);
        assertThat(testAppointmentInfo.getQuotaId()).isEqualTo(UPDATED_QUOTA_ID);
        assertThat(testAppointmentInfo.getServiceType()).isEqualTo(UPDATED_SERVICE_TYPE);
        assertThat(testAppointmentInfo.getApmidType()).isEqualTo(UPDATED_APMID_TYPE);
        assertThat(testAppointmentInfo.getApmidCode()).isEqualTo(UPDATED_APMID_CODE);
        assertThat(testAppointmentInfo.getAppDob()).isEqualTo(UPDATED_APP_DOB);
        assertThat(testAppointmentInfo.getServicesNature()).isEqualTo(UPDATED_SERVICES_NATURE);
        assertThat(testAppointmentInfo.getAgeInd()).isEqualTo(UPDATED_AGE_IND);
        assertThat(testAppointmentInfo.getPrefilInd()).isEqualTo(UPDATED_PREFIL_IND);
        assertThat(testAppointmentInfo.getActive()).isEqualTo(UPDATED_ACTIVE);
        assertThat(testAppointmentInfo.getCreateDate()).isEqualTo(UPDATED_CREATE_DATE);
        assertThat(testAppointmentInfo.getCreateBy()).isEqualTo(UPDATED_CREATE_BY);
        assertThat(testAppointmentInfo.getLastUpdBy()).isEqualTo(UPDATED_LAST_UPD_BY);
        assertThat(testAppointmentInfo.getLastUpdDate()).isEqualTo(UPDATED_LAST_UPD_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingAppointmentInfo() throws Exception {
        int databaseSizeBeforeUpdate = appointmentInfoRepository.findAll().size();

        // Create the AppointmentInfo
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restAppointmentInfoMockMvc.perform(put("/api/appointment-infos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(appointmentInfoDTO)))
            .andExpect(status().isCreated());

        // Validate the AppointmentInfo in the database
        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteAppointmentInfo() throws Exception {
        // Initialize the database
        appointmentInfoRepository.saveAndFlush(appointmentInfo);
        int databaseSizeBeforeDelete = appointmentInfoRepository.findAll().size();

        // Get the appointmentInfo
        restAppointmentInfoMockMvc.perform(delete("/api/appointment-infos/{id}", appointmentInfo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<AppointmentInfo> appointmentInfoList = appointmentInfoRepository.findAll();
        assertThat(appointmentInfoList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppointmentInfo.class);
        AppointmentInfo appointmentInfo1 = new AppointmentInfo();
        appointmentInfo1.setId(1L);
        AppointmentInfo appointmentInfo2 = new AppointmentInfo();
        appointmentInfo2.setId(appointmentInfo1.getId());
        assertThat(appointmentInfo1).isEqualTo(appointmentInfo2);
        appointmentInfo2.setId(2L);
        assertThat(appointmentInfo1).isNotEqualTo(appointmentInfo2);
        appointmentInfo1.setId(null);
        assertThat(appointmentInfo1).isNotEqualTo(appointmentInfo2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AppointmentInfoDTO.class);
        AppointmentInfoDTO appointmentInfoDTO1 = new AppointmentInfoDTO();
        appointmentInfoDTO1.setId(1L);
        AppointmentInfoDTO appointmentInfoDTO2 = new AppointmentInfoDTO();
        assertThat(appointmentInfoDTO1).isNotEqualTo(appointmentInfoDTO2);
        appointmentInfoDTO2.setId(appointmentInfoDTO1.getId());
        assertThat(appointmentInfoDTO1).isEqualTo(appointmentInfoDTO2);
        appointmentInfoDTO2.setId(2L);
        assertThat(appointmentInfoDTO1).isNotEqualTo(appointmentInfoDTO2);
        appointmentInfoDTO1.setId(null);
        assertThat(appointmentInfoDTO1).isNotEqualTo(appointmentInfoDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(appointmentInfoMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(appointmentInfoMapper.fromId(null)).isNull();
    }
}
