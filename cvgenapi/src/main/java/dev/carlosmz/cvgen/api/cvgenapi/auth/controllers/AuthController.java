package dev.carlosmz.cvgen.api.cvgenapi.auth.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto.UserLoginDTO;
import dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto.UserRegisterDTO;
import dev.carlosmz.cvgen.api.cvgenapi.auth.models.dto.UserResponseDTO;
import dev.carlosmz.cvgen.api.cvgenapi.auth.services.JwtService;
import dev.carlosmz.cvgen.api.cvgenapi.auth.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@Valid @RequestBody UserRegisterDTO dto) {
        UserResponseDTO userResponseDto = userService.register(dto);
        return ResponseEntity.ok().body(userResponseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody UserLoginDTO dto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);

        return ResponseEntity.ok(Map.of("token", token));
    }

}
