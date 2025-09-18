package dev.carlosmz.cvgen.api.cvgenapi.models.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class ExperienceDTO {

    private Long id;

    @NotBlank(message = "{exp.position.notBlank}")
    @Size(max = 150, message = "{exp.position.size}")
    private String position;

    @NotBlank(message = "{exp.company.notBlank}")
    @Size(max = 150, message = "{exp.company.size}")
    private String company;

    @Size(max = 150, message = "{exp.location.size}")
    private String location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{exp.startDate.notNull}")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Size(max = 3000, message = "{exp.description.size}")
    private String description;

    @Builder.Default
    private List<@Size(max = 500, message = "{exp.highlight.size}") String> highlights = new ArrayList<>();

}
