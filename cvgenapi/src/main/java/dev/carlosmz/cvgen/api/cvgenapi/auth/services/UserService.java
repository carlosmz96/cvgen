package dev.carlosmz.cvgen.api.cvgenapi.auth.services;

import dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto.UserRegisterDTO;
import dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto.UserResponseDTO;

public interface UserService {

    public UserResponseDTO register(UserRegisterDTO dto);

    public UserResponseDTO findByUsername(String username);

}
