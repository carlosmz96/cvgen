package dev.carlosmz.cvgen.api.cvgenapi.models.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.URL;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

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
@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class CurriculumDTO {

    private Long id;

    @NotBlank(message = "{cv.fullName.notBlank}")
    @Size(max = 200, message = "{cv.fullName.size}")
    private String fullName;

    @NotBlank(message = "{cv.title.notBlank}")
    @Size(max = 120, message = "{cv.title.size}")
    private String title;

    @NotBlank(message = "{cv.email.notBlank}")
    @Email(message = "{cv.email.email}")
    @Size(max = 320, message = "{cv.email.size}")
    private String email;

    @NotBlank(message = "{cv.locationCity.notBlank}")
    @Size(max = 120, message = "{cv.locationCity.size}")
    private String locationCity;

    @NotBlank(message = "{cv.locationCountry.notBlank}")
    @Size(max = 120, message = "{cv.locationCountry.size}")
    private String locationCountry;

    @Size(max = 2000, message = "{cv.summary.size}")
    private String summary;

    @URL(message = "{cv.linkedinUrl.url}")
    @Size(max = 512, message = "{cv.linkedinUrl.size}")
    private String linkedinUrl;

    @URL(message = "{cv.githubUrl.url}")
    @Size(max = 512, message = "{cv.githubUrl.size}")
    private String githubUrl;

    @URL(message = "{cv.portfolioUrl.url}")
    @Size(max = 512, message = "{cv.portfolioUrl.size}")
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
