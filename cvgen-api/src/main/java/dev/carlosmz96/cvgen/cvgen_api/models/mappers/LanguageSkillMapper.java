package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.LanguageSkillDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.LanguageSkill;

@Mapper(componentModel = "spring")
public interface LanguageSkillMapper {

    @Mapping(target = "curriculum", ignore = true)
    LanguageSkill languageSkillDtoToLanguageSkill(LanguageSkillDTO languageSkillDto);
    LanguageSkillDTO languageSkillToLanguageSkillDTO(LanguageSkill languageSkill);
    
}
