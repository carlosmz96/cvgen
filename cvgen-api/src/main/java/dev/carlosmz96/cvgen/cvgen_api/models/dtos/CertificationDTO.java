package dev.carlosmz96.cvgen.cvgen_api.models.dtos;

import java.time.LocalDate;

import lombok.Data;

@Data
public class CertificationDTO {

    private Long id;
    private String name;
    private String issuer;
    private LocalDate obtainedDate;
    private LocalDate validUntil;
    private String credentialUrl;
    private CurriculumDTO curriculum;

}
