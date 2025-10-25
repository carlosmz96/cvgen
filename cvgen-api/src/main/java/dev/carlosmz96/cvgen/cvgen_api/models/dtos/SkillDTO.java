package dev.carlosmz96.cvgen.cvgen_api.models.dtos;

import lombok.Data;

@Data
public class SkillDTO {

    private Long id;
    private String name;
    private String level;
    private String category;
    private CurriculumDTO curriculum;

}
