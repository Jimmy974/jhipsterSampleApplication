package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.QuotaPlan;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the QuotaPlan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface QuotaPlanRepository extends JpaRepository<QuotaPlan, Long> {

}
