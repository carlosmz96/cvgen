package dev.carlosmz96.cvgen.cvgen_api.models.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class EducationDTO {

    private Long id;
    private String degree;
    private String institution;
    private String fieldOfStudy;
    private LocalDate startDate;
    private LocalDate endDate;
    private String description;

}
