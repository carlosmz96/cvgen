package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.EducationDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Education;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    EducationMapper INSTANCE = Mappers.getMapper(EducationMapper.class);

    Education educationDtoToEducation(EducationDTO educationDto);
    EducationDTO educationToEducationDTO(Education education);
    
}
