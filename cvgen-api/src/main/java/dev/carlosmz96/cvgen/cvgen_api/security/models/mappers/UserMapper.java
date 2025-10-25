package dev.carlosmz96.cvgen.cvgen_api.security.models.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import dev.carlosmz96.cvgen.cvgen_api.security.models.dtos.UserDTO;
import dev.carlosmz96.cvgen.cvgen_api.security.models.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userDtoToUser(UserDTO userDto);
    UserDTO userToUserDTO(User user);

}
