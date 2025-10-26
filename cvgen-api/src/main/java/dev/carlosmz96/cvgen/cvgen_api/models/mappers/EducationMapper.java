package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.EducationDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Education;

@Mapper(componentModel = "spring")
public interface EducationMapper {

    @Mapping(target = "curriculum", ignore = true)
    Education educationDtoToEducation(EducationDTO educationDto);
    EducationDTO educationToEducationDTO(Education education);
    
}
