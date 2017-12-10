package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.AppointmentInfo;

import io.github.jhipster.application.repository.AppointmentInfoRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.AppointmentInfoDTO;
import io.github.jhipster.application.service.mapper.AppointmentInfoMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing AppointmentInfo.
 */
@RestController
@RequestMapping("/api")
public class AppointmentInfoResource {

    private final Logger log = LoggerFactory.getLogger(AppointmentInfoResource.class);

    private static final String ENTITY_NAME = "appointmentInfo";

    private final AppointmentInfoRepository appointmentInfoRepository;

    private final AppointmentInfoMapper appointmentInfoMapper;

    public AppointmentInfoResource(AppointmentInfoRepository appointmentInfoRepository, AppointmentInfoMapper appointmentInfoMapper) {
        this.appointmentInfoRepository = appointmentInfoRepository;
        this.appointmentInfoMapper = appointmentInfoMapper;
    }

    /**
     * POST  /appointment-infos : Create a new appointmentInfo.
     *
     * @param appointmentInfoDTO the appointmentInfoDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appointmentInfoDTO, or with status 400 (Bad Request) if the appointmentInfo has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/appointment-infos")
    @Timed
    public ResponseEntity<AppointmentInfoDTO> createAppointmentInfo(@Valid @RequestBody AppointmentInfoDTO appointmentInfoDTO) throws URISyntaxException {
        log.debug("REST request to save AppointmentInfo : {}", appointmentInfoDTO);
        if (appointmentInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new appointmentInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppointmentInfo appointmentInfo = appointmentInfoMapper.toEntity(appointmentInfoDTO);
        appointmentInfo = appointmentInfoRepository.save(appointmentInfo);
        AppointmentInfoDTO result = appointmentInfoMapper.toDto(appointmentInfo);
        return ResponseEntity.created(new URI("/api/appointment-infos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /appointment-infos : Updates an existing appointmentInfo.
     *
     * @param appointmentInfoDTO the appointmentInfoDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appointmentInfoDTO,
     * or with status 400 (Bad Request) if the appointmentInfoDTO is not valid,
     * or with status 500 (Internal Server Error) if the appointmentInfoDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/appointment-infos")
    @Timed
    public ResponseEntity<AppointmentInfoDTO> updateAppointmentInfo(@Valid @RequestBody AppointmentInfoDTO appointmentInfoDTO) throws URISyntaxException {
        log.debug("REST request to update AppointmentInfo : {}", appointmentInfoDTO);
        if (appointmentInfoDTO.getId() == null) {
            return createAppointmentInfo(appointmentInfoDTO);
        }
        AppointmentInfo appointmentInfo = appointmentInfoMapper.toEntity(appointmentInfoDTO);
        appointmentInfo = appointmentInfoRepository.save(appointmentInfo);
        AppointmentInfoDTO result = appointmentInfoMapper.toDto(appointmentInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appointmentInfoDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /appointment-infos : get all the appointmentInfos.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of appointmentInfos in body
     */
    @GetMapping("/appointment-infos")
    @Timed
    public List<AppointmentInfoDTO> getAllAppointmentInfos() {
        log.debug("REST request to get all AppointmentInfos");
        List<AppointmentInfo> appointmentInfos = appointmentInfoRepository.findAll();
        return appointmentInfoMapper.toDto(appointmentInfos);
        }

    /**
     * GET  /appointment-infos/:id : get the "id" appointmentInfo.
     *
     * @param id the id of the appointmentInfoDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appointmentInfoDTO, or with status 404 (Not Found)
     */
    @GetMapping("/appointment-infos/{id}")
    @Timed
    public ResponseEntity<AppointmentInfoDTO> getAppointmentInfo(@PathVariable Long id) {
        log.debug("REST request to get AppointmentInfo : {}", id);
        AppointmentInfo appointmentInfo = appointmentInfoRepository.findOne(id);
        AppointmentInfoDTO appointmentInfoDTO = appointmentInfoMapper.toDto(appointmentInfo);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(appointmentInfoDTO));
    }

    /**
     * DELETE  /appointment-infos/:id : delete the "id" appointmentInfo.
     *
     * @param id the id of the appointmentInfoDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/appointment-infos/{id}")
    @Timed
    public ResponseEntity<Void> deleteAppointmentInfo(@PathVariable Long id) {
        log.debug("REST request to delete AppointmentInfo : {}", id);
        appointmentInfoRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
