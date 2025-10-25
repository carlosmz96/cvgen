package dev.carlosmz96.cvgen.cvgen_api.models.dtos;

import lombok.Data;

@Data
public class LanguageSkillDTO {

    private Long id;
    private String language;
    private String level;
    private CurriculumDTO curriculum;

}
