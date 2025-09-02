package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CertificationDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Certification;

@Mapper(config = GlobalMapperConfig.class)
public interface CertificationMapper {

    @Mapping(target = "curriculum", ignore = true)
    CertificationDTO toDto(Certification entity);

    @Mapping(target = "curriculum", ignore = true)
    Certification toEntity(CertificationDTO dto);

}
