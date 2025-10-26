package dev.carlosmz96.cvgen.cvgen_api.models.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExperienceDTO {

    private Long id;
    private String position;
    private String company;
    private String location;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean current;
    private String description;

}
