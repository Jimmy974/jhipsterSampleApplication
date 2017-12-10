package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.AppointmentTrans;

import io.github.jhipster.application.repository.AppointmentTransRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.AppointmentTransDTO;
import io.github.jhipster.application.service.mapper.AppointmentTransMapper;
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
 * REST controller for managing AppointmentTrans.
 */
@RestController
@RequestMapping("/api")
public class AppointmentTransResource {

    private final Logger log = LoggerFactory.getLogger(AppointmentTransResource.class);

    private static final String ENTITY_NAME = "appointmentTrans";

    private final AppointmentTransRepository appointmentTransRepository;

    private final AppointmentTransMapper appointmentTransMapper;

    public AppointmentTransResource(AppointmentTransRepository appointmentTransRepository, AppointmentTransMapper appointmentTransMapper) {
        this.appointmentTransRepository = appointmentTransRepository;
        this.appointmentTransMapper = appointmentTransMapper;
    }

    /**
     * POST  /appointment-trans : Create a new appointmentTrans.
     *
     * @param appointmentTransDTO the appointmentTransDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new appointmentTransDTO, or with status 400 (Bad Request) if the appointmentTrans has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/appointment-trans")
    @Timed
    public ResponseEntity<AppointmentTransDTO> createAppointmentTrans(@Valid @RequestBody AppointmentTransDTO appointmentTransDTO) throws URISyntaxException {
        log.debug("REST request to save AppointmentTrans : {}", appointmentTransDTO);
        if (appointmentTransDTO.getId() != null) {
            throw new BadRequestAlertException("A new appointmentTrans cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AppointmentTrans appointmentTrans = appointmentTransMapper.toEntity(appointmentTransDTO);
        appointmentTrans = appointmentTransRepository.save(appointmentTrans);
        AppointmentTransDTO result = appointmentTransMapper.toDto(appointmentTrans);
        return ResponseEntity.created(new URI("/api/appointment-trans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /appointment-trans : Updates an existing appointmentTrans.
     *
     * @param appointmentTransDTO the appointmentTransDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated appointmentTransDTO,
     * or with status 400 (Bad Request) if the appointmentTransDTO is not valid,
     * or with status 500 (Internal Server Error) if the appointmentTransDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/appointment-trans")
    @Timed
    public ResponseEntity<AppointmentTransDTO> updateAppointmentTrans(@Valid @RequestBody AppointmentTransDTO appointmentTransDTO) throws URISyntaxException {
        log.debug("REST request to update AppointmentTrans : {}", appointmentTransDTO);
        if (appointmentTransDTO.getId() == null) {
            return createAppointmentTrans(appointmentTransDTO);
        }
        AppointmentTrans appointmentTrans = appointmentTransMapper.toEntity(appointmentTransDTO);
        appointmentTrans = appointmentTransRepository.save(appointmentTrans);
        AppointmentTransDTO result = appointmentTransMapper.toDto(appointmentTrans);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, appointmentTransDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /appointment-trans : get all the appointmentTrans.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of appointmentTrans in body
     */
    @GetMapping("/appointment-trans")
    @Timed
    public List<AppointmentTransDTO> getAllAppointmentTrans() {
        log.debug("REST request to get all AppointmentTrans");
        List<AppointmentTrans> appointmentTrans = appointmentTransRepository.findAll();
        return appointmentTransMapper.toDto(appointmentTrans);
        }

    /**
     * GET  /appointment-trans/:id : get the "id" appointmentTrans.
     *
     * @param id the id of the appointmentTransDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the appointmentTransDTO, or with status 404 (Not Found)
     */
    @GetMapping("/appointment-trans/{id}")
    @Timed
    public ResponseEntity<AppointmentTransDTO> getAppointmentTrans(@PathVariable Long id) {
        log.debug("REST request to get AppointmentTrans : {}", id);
        AppointmentTrans appointmentTrans = appointmentTransRepository.findOne(id);
        AppointmentTransDTO appointmentTransDTO = appointmentTransMapper.toDto(appointmentTrans);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(appointmentTransDTO));
    }

    /**
     * DELETE  /appointment-trans/:id : delete the "id" appointmentTrans.
     *
     * @param id the id of the appointmentTransDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/appointment-trans/{id}")
    @Timed
    public ResponseEntity<Void> deleteAppointmentTrans(@PathVariable Long id) {
        log.debug("REST request to delete AppointmentTrans : {}", id);
        appointmentTransRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
