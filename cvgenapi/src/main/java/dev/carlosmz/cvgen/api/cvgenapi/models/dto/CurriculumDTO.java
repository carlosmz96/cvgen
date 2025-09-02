package dev.carlosmz.cvgen.api.cvgenapi.models.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
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
public class CurriculumDTO {

    private Long id;

    @NotBlank
    @Size(max = 200)
    private String fullName;

    @NotBlank
    @Size(max = 120)
    private String title;

    @NotBlank
    @Email
    @Size(max = 320)
    private String email;

    @NotBlank
    @Size(max = 120)
    private String locationCity;

    @NotBlank
    @Size(max = 120)
    private String locationCountry;

    @Size(max = 2000)
    private String summary;

    @URL @Size(max = 512)
    private String linkedinUrl;

    @URL @Size(max = 512)
    private String githubUrl;

    @URL @Size(max = 512)
    private String portfolioUrl;

    private Instant createdAt;
    private Instant updatedAt;

    @Builder.Default
    private List<ExperienceDTO> experiences = new ArrayList<>();

    @Builder.Default
    private List<SkillDTO> skills = new ArrayList<>();

    @Builder.Default
    private List<EducationDTO> educations = new ArrayList<>();

    @Builder.Default
    private List<CertificationDTO> certifications = new ArrayList<>();

}
