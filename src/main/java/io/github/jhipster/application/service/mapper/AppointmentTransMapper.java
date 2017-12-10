package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.AppointmentTransDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AppointmentTrans and its DTO AppointmentTransDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppointmentTransMapper extends EntityMapper<AppointmentTransDTO, AppointmentTrans> {

    

    

    default AppointmentTrans fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppointmentTrans appointmentTrans = new AppointmentTrans();
        appointmentTrans.setId(id);
        return appointmentTrans;
    }
}
