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

import dev.carlosmz96.cvgen.cvgen_api.enums.Role;
import dev.carlosmz96.cvgen.cvgen_api.security.models.dtos.AuthRequest;
import dev.carlosmz96.cvgen.cvgen_api.security.models.dtos.AuthResponse;
import dev.carlosmz96.cvgen.cvgen_api.security.models.dtos.RegisterRequest;
import dev.carlosmz96.cvgen.cvgen_api.security.models.dtos.UserDTO;
import dev.carlosmz96.cvgen.cvgen_api.security.services.JwtService;
import dev.carlosmz96.cvgen.cvgen_api.services.UserService;
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
        if (userService.existePorEmail(request.email())) return ResponseEntity.badRequest().build();

        UserDTO userDto = new UserDTO();
        userDto.setName(request.name());
        userDto.setEmail(request.email());
        userDto.setPassword(passwordEncoder.encode(request.password()));
        userDto.setRole(Role.USER);
        userService.registrarUsuario(userDto);

        String access  = jwtService.generateToken(userDto.getEmail(),
                Map.of("role", userDto.getRole().name(), "name", userDto.getName()));
        String refresh = jwtService.generateRefreshToken(userDto.getEmail());

        return ResponseEntity.ok(new AuthResponse(access, refresh));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        UserDTO userDto = userService.obtenerPorEmail(request.email());

        String access  = jwtService.generateToken(userDto.getEmail(),
                Map.of("role", userDto.getRole().name(), "name", userDto.getName()));
        String refresh = jwtService.generateRefreshToken(userDto.getEmail());

        return ResponseEntity.ok(new AuthResponse(access, refresh));
    }

    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody Map<String, String> body) {
        String refresh = body.get("refreshToken");
        if (refresh == null || !jwtService.isRefreshToken(refresh)) return ResponseEntity.status(401).build();

        String email = jwtService.extractSubject(refresh);
        if (!jwtService.isValid(refresh, email)) return ResponseEntity.status(401).build();

        UserDTO userDto = userService.obtenerPorEmail(email);

        String accessNew  = jwtService.generateToken(userDto.getEmail(),
                Map.of("role", userDto.getRole().name(), "name", userDto.getName()));
        String refreshNew = jwtService.generateRefreshToken(userDto.getEmail()); // rotación

        return ResponseEntity.ok(new AuthResponse(accessNew, refreshNew));
    }

}
