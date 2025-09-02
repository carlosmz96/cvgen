package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.EducationDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Education;

@Mapper(config = GlobalMapperConfig.class)
public interface EducationMapper {

    @Mapping(target = "curriculum", ignore = true)
    EducationDTO toDto(Education entity);

    @Mapping(target = "curriculum", ignore = true)
    Education toEntity(EducationDTO dto);

}
