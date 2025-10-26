package dev.carlosmz96.cvgen.cvgen_api.security.models.dtos;

import java.time.Instant;

import dev.carlosmz96.cvgen.cvgen_api.enums.Role;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private Instant createdAt;
    private Instant updatedAt;

}
