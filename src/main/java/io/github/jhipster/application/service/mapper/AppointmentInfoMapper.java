package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.AppointmentInfoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity AppointmentInfo and its DTO AppointmentInfoDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface AppointmentInfoMapper extends EntityMapper<AppointmentInfoDTO, AppointmentInfo> {

    

    

    default AppointmentInfo fromId(Long id) {
        if (id == null) {
            return null;
        }
        AppointmentInfo appointmentInfo = new AppointmentInfo();
        appointmentInfo.setId(id);
        return appointmentInfo;
    }
}
