package dev.carlosmz.cvgen.api.cvgenapi.models.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "educations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "curriculum_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curriculum curriculum;

    @Column(nullable = false)
    private String degree;

    @Column(nullable = false)
    private String educationField;

    @Column(nullable = false)
    private String institution;

    private String location;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(length = 2000)
    private String description; // detalles relevantes, proyectos, nota media...

}
