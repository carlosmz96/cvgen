package dev.carlosmz96.cvgen.cvgen_api.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.ExperienceDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.Experience;

@Mapper(componentModel = "spring")
public interface ExperienceMapper {

    ExperienceMapper INSTANCE = Mappers.getMapper(ExperienceMapper.class);

    Experience experienceDtoToExperience(ExperienceDTO experienceDto);
    ExperienceDTO experienceToExperienceDTO(Experience experience);
    
}
