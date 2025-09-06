package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.Mapper;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.SkillDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Skill;

@Mapper(config = GlobalMapperConfig.class)
public interface SkillMapper {

    SkillDTO toDto(Skill entity);

    Skill toEntity(SkillDTO dto);

}
