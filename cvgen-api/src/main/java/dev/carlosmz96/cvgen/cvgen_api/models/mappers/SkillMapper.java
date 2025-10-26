package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.SkillDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Skill;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    @Mapping(target = "curriculum", ignore = true)
    Skill skillDtoToSkill(SkillDTO skillDto);
    SkillDTO skillToSkillDTO(Skill skill);
    
}
