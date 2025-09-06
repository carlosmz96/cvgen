package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.Mapper;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.ExperienceDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Experience;

@Mapper(config = GlobalMapperConfig.class)
public interface ExperienceMapper {

    ExperienceDTO toDto(Experience entity);

    Experience toEntity(ExperienceDTO dto);

}
