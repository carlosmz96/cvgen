package dev.carlosmz96.cvgen.cvgen_api.models.dtos.security;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;
import dev.carlosmz96.cvgen.cvgen_api.models.entities.security.Role;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Role role;
    private List<CurriculumDTO> curriculums = new ArrayList<>();
    private Instant createdAt;
    private Instant updatedAt;

}
