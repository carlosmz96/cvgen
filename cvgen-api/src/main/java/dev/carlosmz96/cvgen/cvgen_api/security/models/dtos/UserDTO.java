package dev.carlosmz96.cvgen.cvgen_api.security.models.dtos;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import dev.carlosmz96.cvgen.cvgen_api.enums.Role;
import dev.carlosmz96.cvgen.cvgen_api.models.dtos.CurriculumDTO;
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
