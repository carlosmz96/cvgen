package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Curriculum;

@Mapper(componentModel = "spring")
public interface CurriculumMapper {
    
    CurriculumMapper INSTANCE = Mappers.getMapper(CurriculumMapper.class);

    Curriculum curriculumDtoToCurriculum(CurriculumDTO curriculumDto);
    CurriculumDTO curriculumToCurriculumDTO(Curriculum curriculum);

}
