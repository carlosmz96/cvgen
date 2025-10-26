package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Curriculum;
import dev.carlosmz96.cvgen.cvgen_api.security.models.mappers.UserMapper;

@Mapper(componentModel = "spring", uses = {
        ExperienceMapper.class,
        EducationMapper.class,
        SkillMapper.class,
        CertificationMapper.class,
        LanguageSkillMapper.class,
        UserMapper.class
}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CurriculumMapper {

    Curriculum curriculumDtoToCurriculum(CurriculumDTO curriculumDto);

    CurriculumDTO curriculumToCurriculumDTO(Curriculum curriculum);

    @AfterMapping
    default void linkChildren(@MappingTarget Curriculum entity) {
        if (entity.getExperiences() != null)
            entity.getExperiences().forEach(e -> e.setCurriculum(entity));
        if (entity.getEducations() != null)
            entity.getEducations().forEach(e -> e.setCurriculum(entity));
        if (entity.getSkills() != null)
            entity.getSkills().forEach(s -> s.setCurriculum(entity));
        if (entity.getCertifications() != null)
            entity.getCertifications().forEach(c -> c.setCurriculum(entity));
        if (entity.getLanguageSkills() != null)
            entity.getLanguageSkills().forEach(l -> l.setCurriculum(entity));
    }

}
