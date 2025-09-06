package dev.carlosmz.cvgen.api.cvgenapi.models.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;
import dev.carlosmz.cvgen.api.cvgenapi.models.dto.CurriculumDTO;
import dev.carlosmz.cvgen.api.cvgenapi.models.entities.Curriculum;

@Mapper(config = GlobalMapperConfig.class)
public interface CurriculumMapper {

    CurriculumDTO toDto(Curriculum entity);

    Curriculum toEntity(CurriculumDTO dto);

    @AfterMapping
    default void linkChildren(@MappingTarget Curriculum curriculum) {
        curriculum.getExperiences().forEach(exp -> exp.setCurriculum(curriculum));
        curriculum.getEducations().forEach(edu -> edu.setCurriculum(curriculum));
        curriculum.getSkills().forEach(skill -> skill.setCurriculum(curriculum));
        curriculum.getCertifications().forEach(cert -> cert.setCurriculum(curriculum));
    }

}
