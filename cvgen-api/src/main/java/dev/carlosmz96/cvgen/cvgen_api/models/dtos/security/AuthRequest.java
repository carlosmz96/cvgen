package dev.carlosmz96.cvgen.cvgen_api.models.dtos.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
    @Email @NotBlank String email,
    @NotBlank String password
) {}
