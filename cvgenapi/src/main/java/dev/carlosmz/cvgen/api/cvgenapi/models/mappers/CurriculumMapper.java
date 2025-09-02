package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.Mapper;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CurriculumDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Curriculum;

@Mapper(config = GlobalMapperConfig.class)
public interface CurriculumMapper {

    CurriculumDTO toDto(Curriculum entity);

    Curriculum toEntity(CurriculumDTO dto);

}
