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

    @NotBlank(message = "{edu.degree.notBlank}")
    @Size(max = 150, message = "{edu.degree.size}")
    private String degree;

    @NotBlank(message = "{edu.educationField.notBlank}")
    @Size(max = 150, message = "{edu.educationField.size}")
    private String educationField;

    @NotBlank(message = "{edu.institution.notBlank}")
    @Size(max = 200, message = "{edu.institution.size}")
    private String institution;

    @Size(max = 150, message = "{edu.location.size}")
    private String location;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "{edu.startDate.notNull}")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Size(max = 2000, message = "{edu.description.size}")
    private String description;

}
