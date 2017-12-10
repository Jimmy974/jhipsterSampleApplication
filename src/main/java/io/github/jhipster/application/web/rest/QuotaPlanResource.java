package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.QuotaPlan;

import io.github.jhipster.application.repository.QuotaPlanRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.QuotaPlanDTO;
import io.github.jhipster.application.service.mapper.QuotaPlanMapper;
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
 * REST controller for managing QuotaPlan.
 */
@RestController
@RequestMapping("/api")
public class QuotaPlanResource {

    private final Logger log = LoggerFactory.getLogger(QuotaPlanResource.class);

    private static final String ENTITY_NAME = "quotaPlan";

    private final QuotaPlanRepository quotaPlanRepository;

    private final QuotaPlanMapper quotaPlanMapper;

    public QuotaPlanResource(QuotaPlanRepository quotaPlanRepository, QuotaPlanMapper quotaPlanMapper) {
        this.quotaPlanRepository = quotaPlanRepository;
        this.quotaPlanMapper = quotaPlanMapper;
    }

    /**
     * POST  /quota-plans : Create a new quotaPlan.
     *
     * @param quotaPlanDTO the quotaPlanDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new quotaPlanDTO, or with status 400 (Bad Request) if the quotaPlan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/quota-plans")
    @Timed
    public ResponseEntity<QuotaPlanDTO> createQuotaPlan(@Valid @RequestBody QuotaPlanDTO quotaPlanDTO) throws URISyntaxException {
        log.debug("REST request to save QuotaPlan : {}", quotaPlanDTO);
        if (quotaPlanDTO.getId() != null) {
            throw new BadRequestAlertException("A new quotaPlan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        QuotaPlan quotaPlan = quotaPlanMapper.toEntity(quotaPlanDTO);
        quotaPlan = quotaPlanRepository.save(quotaPlan);
        QuotaPlanDTO result = quotaPlanMapper.toDto(quotaPlan);
        return ResponseEntity.created(new URI("/api/quota-plans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /quota-plans : Updates an existing quotaPlan.
     *
     * @param quotaPlanDTO the quotaPlanDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated quotaPlanDTO,
     * or with status 400 (Bad Request) if the quotaPlanDTO is not valid,
     * or with status 500 (Internal Server Error) if the quotaPlanDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/quota-plans")
    @Timed
    public ResponseEntity<QuotaPlanDTO> updateQuotaPlan(@Valid @RequestBody QuotaPlanDTO quotaPlanDTO) throws URISyntaxException {
        log.debug("REST request to update QuotaPlan : {}", quotaPlanDTO);
        if (quotaPlanDTO.getId() == null) {
            return createQuotaPlan(quotaPlanDTO);
        }
        QuotaPlan quotaPlan = quotaPlanMapper.toEntity(quotaPlanDTO);
        quotaPlan = quotaPlanRepository.save(quotaPlan);
        QuotaPlanDTO result = quotaPlanMapper.toDto(quotaPlan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, quotaPlanDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /quota-plans : get all the quotaPlans.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of quotaPlans in body
     */
    @GetMapping("/quota-plans")
    @Timed
    public List<QuotaPlanDTO> getAllQuotaPlans() {
        log.debug("REST request to get all QuotaPlans");
        List<QuotaPlan> quotaPlans = quotaPlanRepository.findAll();
        return quotaPlanMapper.toDto(quotaPlans);
        }

    /**
     * GET  /quota-plans/:id : get the "id" quotaPlan.
     *
     * @param id the id of the quotaPlanDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the quotaPlanDTO, or with status 404 (Not Found)
     */
    @GetMapping("/quota-plans/{id}")
    @Timed
    public ResponseEntity<QuotaPlanDTO> getQuotaPlan(@PathVariable Long id) {
        log.debug("REST request to get QuotaPlan : {}", id);
        QuotaPlan quotaPlan = quotaPlanRepository.findOne(id);
        QuotaPlanDTO quotaPlanDTO = quotaPlanMapper.toDto(quotaPlan);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(quotaPlanDTO));
    }

    /**
     * DELETE  /quota-plans/:id : delete the "id" quotaPlan.
     *
     * @param id the id of the quotaPlanDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/quota-plans/{id}")
    @Timed
    public ResponseEntity<Void> deleteQuotaPlan(@PathVariable Long id) {
        log.debug("REST request to delete QuotaPlan : {}", id);
        quotaPlanRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
