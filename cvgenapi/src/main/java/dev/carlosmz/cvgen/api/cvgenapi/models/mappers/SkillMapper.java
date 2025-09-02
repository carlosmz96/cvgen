package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.SkillDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Skill;

@Mapper(config = GlobalMapperConfig.class)
public interface SkillMapper {

    @Mapping(target = "curriculum", ignore = true)
    SkillDTO toDto(Skill entity);

    @Mapping(target = "curriculum", ignore = true)
    Skill toEntity(SkillDTO dto);

}
