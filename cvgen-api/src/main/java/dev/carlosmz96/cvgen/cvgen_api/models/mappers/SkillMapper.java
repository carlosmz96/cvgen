package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.SkillDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Skill;

@Mapper(componentModel = "spring")
public interface SkillMapper {

    SkillMapper INSTANCE = Mappers.getMapper(SkillMapper.class);

    Skill skillDtoToSkill(SkillDTO skillDto);
    SkillDTO skillToSkillDTO(Skill skill);
    
}
