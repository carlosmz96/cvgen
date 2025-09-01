package dev.carlosmz.cvgen.api.cvgenapi.models.entities;

import java.util.List;

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
@Table(name = "experiences")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Experience {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "curriculum_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Curriculum curriculum;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private String company;

    private String location;

    @Column(name = "start_date", nullable = false)
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    private String description; // texto libre

    private List<String> highlights; // lista corta de logros

}
