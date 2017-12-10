package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.QuotaPlanDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity QuotaPlan and its DTO QuotaPlanDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface QuotaPlanMapper extends EntityMapper<QuotaPlanDTO, QuotaPlan> {

    

    

    default QuotaPlan fromId(Long id) {
        if (id == null) {
            return null;
        }
        QuotaPlan quotaPlan = new QuotaPlan();
        quotaPlan.setId(id);
        return quotaPlan;
    }
}
