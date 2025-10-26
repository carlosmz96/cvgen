package dev.carlosmz96.cvgen.cvgen_api.models.entities;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "certification")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String issuer;
    private LocalDate obtainedDate;
    private LocalDate validUntil;
    private String credentialUrl;

    @ManyToOne
    @JoinColumn(name = "curriculum_id")
    private Curriculum curriculum;
    
}
