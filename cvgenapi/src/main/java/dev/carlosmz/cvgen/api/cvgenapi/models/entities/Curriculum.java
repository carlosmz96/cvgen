package dev.carlosmz.cvgen.api.cvgenapi.models.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "curriculums")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Curriculum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "full_name",nullable = false)
    private String fullname;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String email;

    @Column(name = "location_city", nullable = false)
    private String locationCity;

    @Column(name = "location_country", nullable = false)
    private String locationCountry;

    private String summary; // perfil profesional

    @Column(name = "linkedin_url")
    private String linkedinUrl;

    @Column(name = "github_url")
    private String githubUrl;

    @Column(name = "portfolio_url")
    private String portfolioUrl;

    private Instant createdAt;

    private Instant updatedAt;

}
