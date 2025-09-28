package dev.carlosmz.cvgen.api.cvgenapi.auth.services.impl;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto.UserRegisterDTO;
import dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto.UserResponseDTO;
import dev.carlosmz.cvgen.api.cvgenapi.auth.models.entities.User;
import dev.carlosmz.cvgen.api.cvgenapi.auth.models.mappers.UserMapper;
import dev.carlosmz.cvgen.api.cvgenapi.auth.repositories.UserRepository;
import dev.carlosmz.cvgen.api.cvgenapi.auth.services.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDTO register(UserRegisterDTO dto) {
        if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("El usuario ya existe.");
        }

        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Ya existe un usuario con este correo electronico.");
        }

        User user = User.builder()
                .fullName(dto.getFullName())
                .email(dto.getEmail())
                .username(dto.getUsername())
                .password(passwordEncoder.encode(dto.getPassword()))
                .build();

        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserResponseDTO findByUsername(String username) {
        return userMapper.toDto(userRepository.findByUsername(username).orElseThrow());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .authorities(Collections.emptyList())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(false)
                .build();
    }

}
