package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.Mapper;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CertificationDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Certification;

@Mapper(config = GlobalMapperConfig.class)
public interface CertificationMapper {

    CertificationDTO toDto(Certification entity);

    Certification toEntity(CertificationDTO dto);

}
