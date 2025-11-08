package dev.carlosmz96.cvgen.cvgen_api.models.dtos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dev.carlosmz96.cvgen.cvgen_api.security.models.dtos.UserDTO;
import lombok.Data;

@Data
public class CurriculumDTO {

    private Long id;
    private String country;
    private String city;
    private String phone;
    private String title;
    private String summary;
    private String theme;
    private String language;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    private UserDTO user;

    private List<ExperienceDTO> experiences = new ArrayList<>();
    private List<EducationDTO> educations = new ArrayList<>();
    private List<SkillDTO> skills = new ArrayList<>();
    private List<CertificationDTO> certifications = new ArrayList<>();
    private List<LanguageSkillDTO> languageSkills = new ArrayList<>();

}
