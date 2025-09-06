package dev.carlosmz.cvgen.api.cvgenapi.models.dto;

import java.time.LocalDate;

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
public class EducationDTO {

    private Long id;

    @NotBlank @Size(max = 150)
    private String degree;

    @NotBlank @Size(max = 150)
    private String educationField;

    @NotBlank @Size(max = 200)
    private String institution;

    @Size(max = 150)
    private String location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Size(max = 2000)
    private String description;

}
