package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CertificationDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Certification;

@Mapper(componentModel = "spring")
public interface CertificationMapper {

    @Mapping(target = "curriculum", ignore = true)
    Certification certificationDtoToCertification(CertificationDTO certificationDto);
    CertificationDTO certificationToCertificationDTO(Certification certification);

}
