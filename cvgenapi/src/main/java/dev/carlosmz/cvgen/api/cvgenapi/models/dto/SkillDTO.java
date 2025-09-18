package dev.carlosmz.cvgen.api.cvgenapi.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SkillDTO {

    private Long id;

    @NotBlank(message = "{skill.name.notBlank}")
    @Size(max = 100, message = "{skill.name.size}")
    private String name;

    @Size(max = 50, message = "{skill.level.size}")
    private String level;

    @Size(max = 50, message = "{skill.category.size}")
    private String category;

}
