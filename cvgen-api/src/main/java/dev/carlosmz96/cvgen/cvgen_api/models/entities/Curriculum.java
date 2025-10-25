package dev.carlosmz96.cvgen.cvgen_api.models.entities;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Curriculum")
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String summary;
    private String theme;
    private String language;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<Education> educations;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<Skill> skills;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<Certification> certifications;

    @OneToMany(mappedBy = "curriculum", cascade = CascadeType.ALL)
    private List<LanguageSkill> languageSkills;

}
