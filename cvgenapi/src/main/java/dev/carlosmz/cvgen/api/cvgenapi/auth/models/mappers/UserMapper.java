package dev.carlosmz.cvgen.api.cvgenapi.auth.models.mappers;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;

import dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto.UserRegisterDTO;
import dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto.UserResponseDTO;
import dev.carlosmz.cvgen.api.cvgenapi.auth.models.entities.User;
import dev.carlosmz.cvgen.api.cvgenapi.config.GlobalMapperConfig;

@Mapper(config = GlobalMapperConfig.class, builder = @Builder(disableBuilder = true))
public interface UserMapper {

    UserResponseDTO toDto(User entity);

    User toEntity(UserRegisterDTO dto);
    
}
