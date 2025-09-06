package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.Mapper;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.EducationDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Education;

@Mapper(config = GlobalMapperConfig.class)
public interface EducationMapper {

    EducationDTO toDto(Education entity);

    Education toEntity(EducationDTO dto);

}
