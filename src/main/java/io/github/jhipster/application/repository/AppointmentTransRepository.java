package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AppointmentTrans;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AppointmentTrans entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentTransRepository extends JpaRepository<AppointmentTrans, Long> {

}
