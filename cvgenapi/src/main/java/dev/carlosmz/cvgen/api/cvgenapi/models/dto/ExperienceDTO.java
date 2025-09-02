package dev.carlosmz.cvgen.api.cvgenapi.models.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @JsonIgnore
    private CurriculumDTO curriculum;

    @NotBlank @Size(max = 150)
    private String position;

    @NotBlank @Size(max = 150)
    private String company;

    @Size(max = 150)
    private String location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    @NotNull
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @Size(max = 3000)
    private String description;

    @Builder.Default
    private List<@Size(max = 500) String> highlights = new ArrayList<>();

}
