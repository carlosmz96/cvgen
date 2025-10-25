package dev.carlosmz96.cvgen.cvgen_api.services.impl;

import org.springframework.stereotype.Service;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.security.UserDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.security.User;
import dev.carlosmz96.cvgen.cvgen_api.models.mappers.UserMapper;
import dev.carlosmz96.cvgen.cvgen_api.repositories.security.UserRepository;
import dev.carlosmz96.cvgen.cvgen_api.services.UserService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public boolean existePorEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public UserDTO obtenerPorEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow();
        return userMapper.userToUserDTO(user);
    }

    @Override
    public void registrarUsuario(UserDTO userDTO) {
        User user = userMapper.userDtoToUser(userDTO);
        userRepository.save(user);
    }

}
