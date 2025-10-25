package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.LanguageSkillDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.LanguageSkill;

@Mapper(componentModel = "spring")
public interface LanguageSkillMapper {

    LanguageSkillMapper INSTANCE = Mappers.getMapper(LanguageSkillMapper.class);

    LanguageSkill languageSkillDtoToLanguageSkill(LanguageSkillDTO languageSkillDto);
    LanguageSkillDTO languageSkillToLanguageSkillDTO(LanguageSkill languageSkill);
    
}
