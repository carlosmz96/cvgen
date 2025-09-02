package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.ExperienceDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Experience;

@Mapper(config = GlobalMapperConfig.class)
public interface ExperienceMapper {

    @Mapping(target = "curriculum", ignore = true)
    ExperienceDTO toDto(Experience entity);

    @Mapping(target = "curriculum", ignore = true)
    Experience toEntity(ExperienceDTO dto);

}
