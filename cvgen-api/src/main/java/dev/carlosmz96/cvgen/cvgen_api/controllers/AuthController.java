package dev.carlosmz96.cvgen.cvgen_api.controllers;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.security.AuthRequest;
import dev.carlosmz96.cvgen.cvgen_api.models.dtos.security.AuthResponse;
import dev.carlosmz96.cvgen.cvgen_api.models.dtos.security.RegisterRequest;
import dev.carlosmz96.cvgen.cvgen_api.models.dtos.security.UserDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.security.Role;
import dev.carlosmz96.cvgen.cvgen_api.services.UserService;
import dev.carlosmz96.cvgen.cvgen_api.services.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        if (userService.existePorEmail(request.email())) {
            return ResponseEntity.badRequest().build();
        }

        UserDTO userDto = new UserDTO();
        userDto.setName(request.name());
        userDto.setEmail(request.email());
        userDto.setPassword(passwordEncoder.encode(request.password()));
        userDto.setRole(Role.USER);
        userService.registrarUsuario(userDto);

        String token = jwtService.generateToken(userDto.getEmail(),
                Map.of("role", userDto.getRole().name(), "name", userDto.getName()));
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        UserDTO userDto = userService.obtenerPorEmail(request.email());
        String token = jwtService.generateToken(userDto.getEmail(),
                Map.of("role", userDto.getRole().name(), "name", userDto.getName()));
        return ResponseEntity.ok(new AuthResponse(token));
    }

}
