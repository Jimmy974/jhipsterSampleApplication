package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.AppointmentInfo;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the AppointmentInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppointmentInfoRepository extends JpaRepository<AppointmentInfo, Long> {

}
